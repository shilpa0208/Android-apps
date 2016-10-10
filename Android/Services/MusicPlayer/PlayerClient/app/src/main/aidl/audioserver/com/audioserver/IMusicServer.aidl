package audioserver.com.audioserver;

interface IMusicServer {

    //Declare the methods to be defined in the Client side.
    void playMusic(int id);
    void stopMusic();
    void pauseMusic();
    void resumeMusic();
    List<String> getTransactions();

}
