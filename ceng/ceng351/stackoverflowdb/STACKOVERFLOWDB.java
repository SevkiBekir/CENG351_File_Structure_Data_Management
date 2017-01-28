package ceng.ceng351.stackoverflowdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ceng.ceng351.stackoverflowdb.QueryResult.NameUsernameDateRatingResult;
import ceng.ceng351.stackoverflowdb.QueryResult.UseridUsernameLastlogindateResult;
import ceng.ceng351.stackoverflowdb.QueryResult.UseridUsernameRegistrationdateWeeklyreputationResult;
import ceng.ceng351.stackoverflowdb.QueryResult.UsernameDateRatingResult;
import ceng.ceng351.stackoverflowdb.QueryResult.UsernameMessageRatingAlltimereputationResult;
import ceng.ceng351.stackoverflowdb.QueryResult.UsernameMessageRatingResult;

/**
 * @author SBK
 * @date Dec 12, 2016
 * @project ceng
 *
 */

public class STACKOVERFLOWDB implements ISTACKOVERFLOWDB {




	private static String user = "username";
    private static String password = "password";
    private static String host = "host";
    private static String database = "db";
    private static int port = 3306;
    private Connection con;

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con =  DriverManager.getConnection(url, this.user, this.password);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

	}

	@Override
	public int createTables() {
		// TODO Auto-generated method stub
		int result;
		int numberofTablesInserted = 0;

		/* ARTICLE TABLE */
		String queryCreatePlayerTable = "create table user (" +
									   "userID char(10) not null," +
									   "username char(30)," +
									   "registrationDate date," +
									   "lastLoginDate date,"+
									   "primary key (userID))";


		try {
			Statement statement = this.con.createStatement();

			//Player Table
			result = statement.executeUpdate(queryCreatePlayerTable);
			System.out.println(result);
			numberofTablesInserted++;

			//close
			statement.close();


		} catch (SQLException e) {
			e.printStackTrace();

		}


		/* ARTICLE TABLE */

		queryCreatePlayerTable = "create table article (" +
							   "articleID char(10) not null,"+
							   "userID char(10)," +
							   "name char(80)," +
							   "description char(130)," +
							   "date date,"+
							   "rating int,"+
							   "primary key (articleID),"+
							   "foreign key(userID) references user(userID)"+
							   "on delete cascade )";


		try {
		Statement statement = this.con.createStatement();

		//Player Table
		result = statement.executeUpdate(queryCreatePlayerTable);
		System.out.println(result);
		numberofTablesInserted++;

		//close
		statement.close();


		} catch (SQLException e) {
		e.printStackTrace();

		}


		/* COMMENT TABLE */
		queryCreatePlayerTable = "create table comment (" +
							   "commentID char(10) not null,"+
							   "articleID char(10)," +
							   "userID char(10),"+
							   "message char(130)," +
							   "date date,"+
							   "rating int,"+
							   "primary key (commentID),"+
							   "foreign key (userID) references user(userID) on delete cascade,"+
							   "foreign key(articleID) references article(articleID)"+
							   "on delete cascade )";


		try {
		Statement statement = this.con.createStatement();

		//Player Table
		result = statement.executeUpdate(queryCreatePlayerTable);
		System.out.println(result);
		numberofTablesInserted++;

		//close
		statement.close();


		} catch (SQLException e) {
		e.printStackTrace();

		}



		/* REPUTATION TABLE */
		queryCreatePlayerTable = "create table reputation (" +
							   "reputationID char(10) not null,"+
							   "userID char(10),"+
							   "weeklyReputation int," +
							   "monthlyReputation int,"+
							   "yearlyReputation int,"+
							   "alltimeReputation int,"+
							   "primary key (reputationID),"+
							   "foreign key(userID) references user(userID)"+
							   "on delete cascade )";


		try {
		Statement statement = this.con.createStatement();

		//Player Table
		result = statement.executeUpdate(queryCreatePlayerTable);
		System.out.println(result);
		numberofTablesInserted++;

		//close
		statement.close();


		} catch (SQLException e) {
		e.printStackTrace();

		}



		return numberofTablesInserted;
	}

	@Override
	public int dropTables() {
		// TODO Auto-generated method stub

		int result;
		int numberofTablesDropped = 0;


		/* DROP COMMENT TABLE */
		String queryDropPlayerTable = "drop table if exists comment";


		try {
			Statement statement = this.con.createStatement();


			result = statement.executeUpdate(queryDropPlayerTable);
			numberofTablesDropped++;
			System.out.println(result);


			//close
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO
		}

		/* DROP REPUTATION TABLE */
		queryDropPlayerTable = "drop table if exists reputation";


		try {
			Statement statement = this.con.createStatement();


			result = statement.executeUpdate(queryDropPlayerTable);
			numberofTablesDropped++;
			System.out.println(result);


			//close
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO
		}


		/* DROP ARTICLE TABLE */

		queryDropPlayerTable = "drop table if exists article";


		try {
			Statement statement = this.con.createStatement();


			result = statement.executeUpdate(queryDropPlayerTable);
			numberofTablesDropped++;
			System.out.println(result);


			//close
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO
		}



		/* DROP USER TABLE */

		queryDropPlayerTable = "drop table if exists user";


		try {
			Statement statement = this.con.createStatement();


			result = statement.executeUpdate(queryDropPlayerTable);
			numberofTablesDropped++;
			System.out.println(result);


			//close
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO
		}



		return numberofTablesDropped;

	}

	@Override
	public int insertUser(User[] users) {
		// TODO Auto-generated method stub
		// user(userID:char(10), username:char(30), registrationDate:date, lastLoginDate:date),
		int numberofInsertUser = 0;
		int result;
		for (int i = 0; i < users.length; i++) {
			String query = "insert into user values (\"" +
					users[i].getuserID() + "\",\"" +
					users[i].getusername() + "\",\"" +
					users[i].getregistrationDate() + "\",\"" +
					users[i].getlastLoginDate() + "\")";

			try {

				Statement st = this.con.createStatement();
				result = st.executeUpdate(query);
				System.out.println(result);
				numberofInsertUser++;
				//Close
				st.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return numberofInsertUser;
	}

	@Override
	public int insertArticle(Article[] articles) {
		// TODO Auto-generated method stubint numberofInsertUser = 0;
		//article(articleID:char(10), userID:char(10), name:char(80), description:char(130), date:date, rating:int),
		int numberofInsertArticle = 0;
		int result;
		for (int i = 0; i < articles.length; i++) {
			System.out.println("Inserting "+ i + "-->");
			String query = "insert into article values (\"" +
					articles[i].getarticleID() + "\",\"" +
					articles[i].getuserID() + "\",\"" +
					articles[i].getname() + "\",\"" +
					articles[i].getdescription()+ "\",\"" +
					articles[i].getdate()+ "\",\"" +
					articles[i].getrating() + "\")";

			System.out.println(query);

			try {

				Statement st = this.con.createStatement();
				result = st.executeUpdate(query);
				System.out.println(result);
				numberofInsertArticle++;
				//Close
				st.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return numberofInsertArticle;
	}

	@Override
	public int insertComment(Comment[] comments) {
		// TODO Auto-generated method stub
		//comment(commentID:char(10), articleID:char(10), userID:char(10), message:char(130), date:date, rating:int),
		int numberofInsertComment = 0;
		int result;
		for (int i = 0; i < comments.length; i++) {
			System.out.println("Inserting "+ i + "-->");
			String query = "insert into comment values (\"" +
					comments[i].getcommentID() + "\",\"" +
					comments[i].getarticleID() + "\",\"" +
					comments[i].getuserID() + "\",\"" +
					comments[i].getmessage() + "\",\"" +
					comments[i].getdate() + "\",\"" +
					comments[i].getrating()+ "\")";

			System.out.println(query);

			try {

				Statement st = this.con.createStatement();
				result = st.executeUpdate(query);
				System.out.println(result);
				numberofInsertComment++;
				//Close
				st.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return numberofInsertComment;
	}

	@Override
	public int insertReputation(Reputation[] reputations) {
		// TODO Auto-generated method stub
		//reputation(reputationID:char(10), userID:char(10), weeklyReputation:int, monthlyReputation:int, yearlyReputation:int,
		//alltimeReputation:int)
		int numberofInsertReputation = 0;
		int result;
		for (int i = 0; i < reputations.length; i++) {
			System.out.println("Inserting "+ i + "-->");
			String query = "insert into reputation values (\"" +
					reputations[i].getreputationID() + "\",\"" +
					reputations[i].getuserID() + "\",\"" +
					reputations[i].getweeklyReputation() + "\",\"" +
					reputations[i].getmonthlyReputation() + "\",\"" +
					reputations[i].getyearlyReputation() + "\",\"" +
					reputations[i].getalltimeReputation()+ "\")";

			System.out.println(query);

			try {

				Statement st = this.con.createStatement();
				result = st.executeUpdate(query);
				System.out.println(result);
				numberofInsertReputation++;
				//Close
				st.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}

		return numberofInsertReputation;

	}

	@Override
	public UsernameDateRatingResult[] getArticleHighestRating() {
		// TODO Auto-generated method stub
		ResultSet rs;

		List<UsernameDateRatingResult>  udrrList = new ArrayList<UsernameDateRatingResult>();

		String query = "select u.username, a.rating, a.date from article a inner join user u on u.userID=a.userID "+
		"where a.rating>= (select max(a1.rating) from article a1) order by u.username asc";

		try {

			Statement st  = this.con.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()){
				UsernameDateRatingResult udrr = null;

				String username = rs.getString("username");
				int rating = rs.getInt("rating");
				String date = rs.getString("date");

				udrr = new UsernameDateRatingResult(username, date, rating);
				udrrList.add(udrr);
			}

		}catch (SQLException e) {
			e.printStackTrace();

		}
		UsernameDateRatingResult[] udrrArray = new UsernameDateRatingResult[udrrList.size()];
		udrrArray = udrrList.toArray(udrrArray);
		return udrrArray;
	}

	@Override
	public UsernameMessageRatingAlltimereputationResult[] getCommentLowestRating() {
		// TODO Auto-generated method stub

		ResultSet rs;

		List<UsernameMessageRatingAlltimereputationResult>  umrarList = new ArrayList<UsernameMessageRatingAlltimereputationResult>();

		String query = " select c.message, c.rating, u.username, r.alltimeReputation from comment c inner join "+
		"user u on u.userID=c.userID inner join reputation r on r.userID = c.userID where message <> '%mysql%' and "+
		"c.rating <= (select min(c1.rating) from comment c1) order by u.username asc";

		try {

			Statement st  = this.con.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()){
				UsernameMessageRatingAlltimereputationResult umrar = null;

				String username = rs.getString("username");
				int rating = rs.getInt("rating");
				String message = rs.getString("message");
				int alltimeReputation = rs.getInt("alltimeReputation");

				umrar = new UsernameMessageRatingAlltimereputationResult(username,message,rating, alltimeReputation);
				umrarList.add(umrar);
			}

		}catch (SQLException e) {
			e.printStackTrace();

		}
		UsernameMessageRatingAlltimereputationResult[] umrarArray = new UsernameMessageRatingAlltimereputationResult[umrarList.size()];
		umrarArray = umrarList.toArray(umrarArray);

		return umrarArray;
	}

	@Override
	public UseridUsernameRegistrationdateWeeklyreputationResult[] getUseridUsernameAfterGivenDate(
			String Date) {
		// TODO Auto-generated method stub

		ResultSet rs;

		List<UseridUsernameRegistrationdateWeeklyreputationResult>  umrarList = new ArrayList<UseridUsernameRegistrationdateWeeklyreputationResult>();

		String query = "select u.userID, u.username, u.registrationDate, r.weeklyReputation from user u "+
		"inner join reputation r on r.userID = u.userID where registrationDate > \""+ Date +"\" order by userID asc";

		try {

			Statement st  = this.con.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()){
				UseridUsernameRegistrationdateWeeklyreputationResult umrar = null;

				String username = rs.getString("username");
				int weeklyReputation = rs.getInt("weeklyReputation");
				String userID = rs.getString("userID");
				String registrationDate = rs.getString("registrationDate");

				umrar = new  UseridUsernameRegistrationdateWeeklyreputationResult(userID, username, registrationDate, weeklyReputation);
				umrarList.add(umrar);
			}

		}catch (SQLException e) {
			e.printStackTrace();

		}
		UseridUsernameRegistrationdateWeeklyreputationResult[] umrarArray = new UseridUsernameRegistrationdateWeeklyreputationResult[umrarList.size()];
		umrarArray = umrarList.toArray(umrarArray);

		return umrarArray;

	}

	@Override
	public UsernameMessageRatingResult[] getUsernameMessageRatingMoreThanGivenRating(
			String message, int rating) {
		// TODO Auto-generated method stub

		ResultSet rs;

		List<UsernameMessageRatingResult>  umrarList = new ArrayList<UsernameMessageRatingResult>();

		String query = "select u.username, c.message, c.rating from comment c inner join user u on u.userID = c.userID "+
				"where message <> \""+message+"\" and rating > "+rating+" order by username asc";
		System.out.println(query);
		try {

			Statement st  = this.con.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()){
				UsernameMessageRatingResult umrar = null;

				String username = rs.getString("username");
				int rating1 = rs.getInt("rating");
				String message1 = rs.getString("message");

				umrar = new  UsernameMessageRatingResult(username, message1, rating1);
				umrarList.add(umrar);
			}

		}catch (SQLException e) {
			e.printStackTrace();

		}
		UsernameMessageRatingResult[] umrarArray = new UsernameMessageRatingResult[umrarList.size()];
		umrarArray = umrarList.toArray(umrarArray);

		return umrarArray;
	}

	@Override
	public int MultiplyComment(String date) {
		// TODO Auto-generated method stub

		int result = 0;;


		String query ="update comment c set c.rating = c.rating*2 where date > \""+date+ "\"";
		System.out.println(query);
		try {

			Statement st  = this.con.createStatement();
			result = st.executeUpdate(query);



		}catch (SQLException e) {
			e.printStackTrace();

		}


		return result;
	}

	@Override
	public UseridUsernameLastlogindateResult[] getUsernameMessageRatingCommentedByGivenUser(
			String userID) {
		// TODO Auto-generated method stub
		ResultSet rs;

		List<UseridUsernameLastlogindateResult>  umrarList = new ArrayList<UseridUsernameLastlogindateResult>();

		String query ="select u.username,u.userID,u.lastLoginDate from user u where not exists (select a2.articleID from comment c2 inner join "+
		"article a2 on a2.articleID = c2.articleID inner join user u2 on c2.userID = u2.userID where c2.userID = \""+userID+"\" and a2.articleID "+
		"not in ( select a1.articleID from comment c1 inner join article a1 on a1.articleID = c1.articleID inner join user u1 on c1.userID = u1.userID "+
		"where c1.userID=u.userID )) and u.userID <>\""+userID+ "\" order by userID asc";

		System.out.println(query);
		try {

			Statement st  = this.con.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()){
				UseridUsernameLastlogindateResult umrar = null;

				String username = rs.getString("username");
				String userID1 = rs.getString("userID");
				String lastLoginDate = rs.getString("lastLoginDate");

				umrar = new  UseridUsernameLastlogindateResult(userID1, username, lastLoginDate);

				umrarList.add(umrar);
			}

		}catch (SQLException e) {
			e.printStackTrace();

		}
		UseridUsernameLastlogindateResult[] umrarArray = new UseridUsernameLastlogindateResult[umrarList.size()];
		umrarArray = umrarList.toArray(umrarArray);

		return umrarArray;
	}

	@Override
	public UsernameMessageRatingResult[] getNameUsernameDateRatingMoreThanGivenArticle(
			int rating, String articleID) {
		// TODO Auto-generated method stub

		ResultSet rs;

		List<UsernameMessageRatingResult>  umrarList = new ArrayList<UsernameMessageRatingResult>();

		String query = "select c.message, u.username, c.rating from comment c inner join article a "+
		"on a.articleID = c.articleID inner join user u on c.userID = u.userID where "+
		"a.articleID = \""+articleID+"\" and c.rating>"+rating;
		System.out.println(query);
		try {

			Statement st  = this.con.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()){
				UsernameMessageRatingResult umrar = null;

				String username = rs.getString("username");
				int rating1 = rs.getInt("rating");
				String message1 = rs.getString("message");

				umrar = new  UsernameMessageRatingResult(username, message1, rating1);
				umrarList.add(umrar);
			}

		}catch (SQLException e) {
			e.printStackTrace();

		}
		UsernameMessageRatingResult[] umrarArray = new UsernameMessageRatingResult[umrarList.size()];
		umrarArray = umrarList.toArray(umrarArray);

		return umrarArray;

	}

	@Override
	public NameUsernameDateRatingResult[] getUsernameDateRatingNotCommentedByAnyUser() {
		// TODO Auto-generated method stub
		ResultSet rs;

		List<NameUsernameDateRatingResult>  umrarList = new ArrayList<NameUsernameDateRatingResult>();

		String query = "select u.username, a.name,a.date,a.rating from comment c inner join "+
		"article a on a.articleID=c.articleID inner join user u on u.userID = c.userID group by a.articleID having count(*) = 1 order by a.name";

		System.out.println(query);
		try {

			Statement st  = this.con.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()){
				NameUsernameDateRatingResult umrar = null;

				String username = rs.getString("username");
				String date = rs.getString("date");
				int rating = rs.getInt("rating");
				String name = rs.getString("name");

				umrar = new  NameUsernameDateRatingResult(name, username, date, rating);

				umrarList.add(umrar);
			}

		}catch (SQLException e) {
			e.printStackTrace();

		}
		NameUsernameDateRatingResult[] umrarArray = new NameUsernameDateRatingResult[umrarList.size()];
		umrarArray = umrarList.toArray(umrarArray);

		return umrarArray;

	}

	@Override
	public UsernameDateRatingResult[] UsernameDateRatingHasHighestReputation(
			String date) {
		// TODO Auto-generated method stub
		ResultSet rs;

		List<UsernameDateRatingResult>  umrarList = new ArrayList<UsernameDateRatingResult>();

		String query = "select u.username, a.date, a.rating from article a inner join user "+
		"u on u.userID = a.userID inner join reputation r on r.userID = u.userID where date< \""+date+"\" and "+
		"r.weeklyReputation = (select max( r.weeklyReputation) from article a inner join user u on u.userID = a.userID "+
		"inner join reputation r on r.userID = u.userID where date< \""+date+"\") order by u.username";

		System.out.println(query);
		try {

			Statement st  = this.con.createStatement();
			rs = st.executeQuery(query);

			while(rs.next()){
				UsernameDateRatingResult umrar = null;

				String username = rs.getString("username");
				String date1 = rs.getString("date");
				int rating = rs.getInt("rating");

				umrar = new  UsernameDateRatingResult(username, date1, rating);

				umrarList.add(umrar);
			}

		}catch (SQLException e) {
			e.printStackTrace();

		}
		UsernameDateRatingResult[] umrarArray = new UsernameDateRatingResult[umrarList.size()];
		umrarArray = umrarList.toArray(umrarArray);

		return umrarArray;
	}

	@Override
	public UsernameDateRatingResult[] UsernameDateRatingDeleteAndSelect(
			String date) {
		// TODO Auto-generated method stub


		ResultSet rs;

		List<UsernameDateRatingResult>  umrarList = new ArrayList<UsernameDateRatingResult>();

		String query = "delete from article where date>\""+date+"\"";
		System.out.println(query);
		try {

			Statement st  = this.con.createStatement();
			st.executeUpdate(query);
			query = "select u.username, a.date, a.rating from article a inner join user u on a.userID = u.userID order by u.username";
			rs = st.executeQuery(query);
			while(rs.next()){
				UsernameDateRatingResult umrar = null;

				String username = rs.getString("username");
				String date1 = rs.getString("date");
				int rating = rs.getInt("rating");


				umrar = new  UsernameDateRatingResult(username, date1, rating);

				umrarList.add(umrar);
			}

		}catch (SQLException e) {
			e.printStackTrace();

		}
		UsernameDateRatingResult[] umrarArray = new UsernameDateRatingResult[umrarList.size()];
		umrarArray = umrarList.toArray(umrarArray);

		return umrarArray;
	}

}
