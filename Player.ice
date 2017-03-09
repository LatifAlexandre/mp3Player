module MP3Player {
	sequence<byte> File;
    interface Player {
        void getInvocationCount(string s);
        void addFile(File f, string name);
        void deleteFile(string name);
        void deleteDirectory(string name);
        int getFileLength(string name);
        string getFilesByRegex(string name);
        File getFile(string name);
    };
};
