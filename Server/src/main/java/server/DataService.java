package server;

public class DataService implements AuthService{


    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return DataBaseConnection.getNickNameByLogAndPass(login,password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return DataBaseConnection.regist(login,password,nickname);
    }

    @Override
    public boolean changeNick(String oldNick, String newNick) {
        return DataBaseConnection.changeNick(newNick,oldNick);
    }
}
