package server;

public class DbHeandler implements AuthService{

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return DataBaseAuthservice.getNickNameByPasswordAndLog(login,password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return DataBaseAuthservice.registration(login,password,nickname);
    }
    @Override
    public boolean changeNick(String oldNickname, String newNickname) {
        return DataBaseAuthservice.changeNick(oldNickname, newNickname);
    }

}
