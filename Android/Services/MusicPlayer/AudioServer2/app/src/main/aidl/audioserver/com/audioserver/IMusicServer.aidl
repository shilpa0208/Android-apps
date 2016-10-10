package audioserver.com.audioserver;

interface IMusicServer {
    //Declare the methods that needs to be defined in this AIDL file.
    void playMusic(int id);
    void stopMusic();
    void pauseMusic();
    void resumeMusic();
    List<String> getTransactions();
}
