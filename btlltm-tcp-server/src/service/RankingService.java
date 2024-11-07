package service;

import connection.DatabaseConnection;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RankingService {
    Connection conn = DatabaseConnection.getInstance().getConnection();
    public List<UserModel> getUserModels(){
        String sql = "SELECT * FROM users";
        List<UserModel> userModelList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                UserModel uM = new UserModel();
                uM.setUserName(resultSet.getNString("username"));
                uM.setScore(resultSet.getFloat("score"));
                userModelList.add(uM);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userModelList;
    }
}
