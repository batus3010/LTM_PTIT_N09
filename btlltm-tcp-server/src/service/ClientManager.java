package service;

import com.google.gson.Gson;
import model.Ranking;
import model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author admin
 */
public class ClientManager {
    ArrayList<Client> clients;

    public ClientManager() {
        clients = new ArrayList<>();
    }

    public boolean add(Client c) {
        if (!clients.contains(c)) {
            clients.add(c);
            return true;
        }
        return true;
    }

    public boolean remove(Client c) {
        if (clients.contains(c)) {
            clients.remove(c);
            return true;
        }
        return false;
    }

    public Client find(String username) {
        for (Client c : clients) {
            if (c.getLoginUser()!= null && c.getLoginUser().equals(username)) {
                return c;
            }
        }
        return null;
    }

    public void broadcast(String msg) {
        clients.forEach((c) -> {
            c.sendData(msg);
        });
    }

    public void sendToAClient (String username, String msg) {
        clients.forEach((c) -> {
            if (c.getLoginUser().equals(username)) {
                c.sendData(msg);
            }
        });
    }
    
    public int getSize() {
        return clients.size();
    }
    
    public String getListUseOnline () {
        String result = "success;" + String.valueOf(clients.size()) + ";";
        for(int i = 0; i < clients.size(); i++) {
            result += clients.get(i).getLoginUser() + ";";
        }
        return result;
    }

    public String getRanking() {
        // Sắp xếp clients theo score giảm dần
        RankingService rankingService = new RankingService();
        List<UserModel> userModelList = rankingService.getUserModels();

        userModelList = userModelList.stream()
                .filter(c -> c.getUserName() != null) // chỉ lấy client đang đăng nhập
                .sorted((c1, c2) -> Float.compare(c2.getScore(), c1.getScore())) // sắp xếp theo score giảm dần
                .collect(Collectors.toList());

        List<Ranking> rankings = new ArrayList<>();
        int rank = 1;
        // Tạo chuỗi kết quả xếp hạng

        for (UserModel userModel : userModelList) {
            Ranking ranking = new Ranking();
            ranking.setUsername(userModel.getUserName());
            ranking.setScore(userModel.getScore());
            ranking.setUserRank(rank);
            rankings.add(ranking);
            rank++;
        }
        Gson gson = new Gson();
        return gson.toJson(rankings);
    }



}
