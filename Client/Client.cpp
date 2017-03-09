#include <Ice/Ice.h>
#include <Player.h>
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <fstream>
#include  <iomanip>
// #include </usr/include/vlc/libvlc.h>
#include </usr/include/vlc/vlc.h>
// #include </usr/include/vlc/libvlc_media_discoverer.h>
// #include </usr/include/vlc/libvlc_media.h>
// #include </usr/include/vlc/libvlc_media_library.h>
// #include </usr/include/vlc/libvlc_media_list.h>
// #include </usr/include/vlc/libvlc_media_list_player.h>
// #include </usr/include/vlc/libvlc_media_player.h>
// #include </usr/include/vlc/libvlc_structures.h>
// #include </usr/include/vlc/libvlc_version.h>
// #include </usr/include/vlc/libvlc_vlm.h>
// #include </usr/include/SFML/Audio.hpp>

using namespace std;
using namespace MP3Player;

typedef unsigned char BYTE;


int getFileSize(FILE* inFile){
  int fileSize = 0;
  fseek(inFile, 0, SEEK_END);

  fileSize = ftell(inFile);

  fseek(inFile, 0, SEEK_SET);
  return fileSize;
}

void sendFile(PlayerPrx player, string name){
  std::vector<BYTE> sound_file;
  std::ifstream _file(name.c_str(), std::ios::binary);
  player->addFile(std::vector<BYTE>((std::istreambuf_iterator<char>(_file)),
  std::istreambuf_iterator<char>()), name);
}

void initUI (PlayerPrx player){
  cout << "Bienvenue dans la meilleure application pour jouer et gérer vos musiqes << MP3 >>!" << endl;
  int choice = 0;
  bool musicPlaying = false;
  string name;
  libvlc_media_player_t *mp; //pour pouvoir arrêter la lécture

  while(choice != 4){
    cout << "1. Jouer une musique" << endl;
    cout << "2. Ajouter une musique (existant dans le fichier files)" << endl;
    cout << "3. Supprimer une musique" << endl;
    cout << "4. Supprimer un dossier" << endl;
    cout << "5. Chercher une musique" << endl;
    cout << "6. Quitter" << endl;
    cout << "Que voulez vous faire ?" <<endl;

    cin >> choice;

    if (choice == 1 || choice == 2 || choice == 3 ){

      cout << "Quel est le nom du morceau (sans l'extension) ? : ";
      cin >> name;
      string completeName = "files/" + name + ".mp3";
      if (choice == 1){
        int duration = player->getFileLength(completeName);
        std::vector<BYTE> byteArray = player->getFile(completeName);
        vector <BYTE>::iterator It;

        ofstream myfile;
        myfile.open(completeName.c_str(), ios::binary);

        for (It = byteArray.begin(); It != byteArray.end(); ++It){
          //cout << *It;
          myfile << *It;
        }
        myfile.close();
        //--------------------------


        libvlc_instance_t *inst;
        libvlc_media_t *m;

        // load the vlc engine
        inst = libvlc_new(0, NULL);

        // create a new item
        m = libvlc_media_new_path(inst, completeName.c_str());

        // create a media play playing environment
        mp = libvlc_media_player_new_from_media(m);

        // no need to keep the media now
        libvlc_media_release(m);

        // play the media_player
        libvlc_media_player_play(mp);

        cout << "duration : " << duration;
        sleep(duration);
        // stop playing
        libvlc_media_player_stop(mp);

        // free the media_player
        libvlc_media_player_release(mp);

        libvlc_release(inst);

      }
      else if (choice == 2){
        sendFile(player, completeName);
      }
      else {
        player->deleteFile(completeName);
      }
    }
    else if (choice == 4){
      cout << "Quel est le nom du dossier ? : ";
      cin >> name;
      player->deleteDirectory(name);

    }
    else if (choice == 5){
      cout << "Quel est le nom de la musique (vous pouvez entrez une partie du nom) ? : ";
      cin >> name;
      string musiques = player->getFilesByRegex(name);
      if (musiques == "") musiques = "La musique n'exist pas";
      cout <<  musiques << endl;
    }
    else if (choice != 6){
      cout << "Veuillez refaire un choix : " << endl;
    }
  }
}


int main(int argc, char* argv[])
{
    int status = 0;
    Ice::CommunicatorPtr ic;
    try {

      Ice::PropertiesPtr props = Ice::createProperties(argc, argv);

      // Make sure that network and protocol tracing are off.
      //
      props->setProperty("Ice.MessageSizeMax", "1048576");

      // Initialize a communicator with these properties.
      //
      Ice::InitializationData id;
      id.properties = props;
      ic = Ice::initialize(id);

      // ic = Ice::initialize(argc, argv);
      Ice::ObjectPrx base = ic->stringToProxy("SimplePlayer:default -p 10000");
      PlayerPrx player = PlayerPrx::checkedCast(base);
      if (!player)
          throw "Invalid proxy";

          initUI(player);

      player->getInvocationCount("Hello World!");





      //------------------

    } catch (const Ice::Exception& ex) {
        cerr << ex << endl;
        status = 1;
    } catch (const char* msg) {
        cerr << msg << endl;
        status = 1;
    }
    if (ic)
        ic->destroy();
    return status;
}
