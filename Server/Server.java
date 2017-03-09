public class Server {
    public static void main(String[] args){
        int status = 0;
        Ice.Communicator ic = null;
        try {

          Ice.StringSeqHolder argsH = new Ice.StringSeqHolder(args);
          Ice.Properties properties = Ice.Util.createProperties(argsH);
          properties.setProperty("Ice.MessageSizeMax", "1048576");
          Ice.InitializationData id = new Ice.InitializationData();
          id.properties = properties;
          ic = Ice.Util.initialize(id);

          // ic = Ice.Util.initialize(args);
          Ice.ObjectAdapter adapter =
              ic.createObjectAdapterWithEndpoints("SimplePlayerAdapter", "default -p 10000");
          Ice.Object object = new PlayerI();
          adapter.add(object, ic.stringToIdentity("SimplePlayer"));
          adapter.activate();
          ic.waitForShutdown();
        } catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (ic != null) {
            // Clean up
            //
            try {
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }
}
