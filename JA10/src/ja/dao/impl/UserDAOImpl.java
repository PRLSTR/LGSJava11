package ja.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ja.dao.DAOException;
import ja.dao.DAOFactory;
import ja.dao.UserDAO;
import ja.domain.User;

public class UserDAOImpl implements UserDAO {
	private Logger log = Logger.getLogger(UserDAOImpl.class);

	@Override
	public User insert(User user)
			throws DAOException {
		log.info("Creating new user in database...");
		String sqlQuery = "insert into user(`first_name`, `last_name`, `email`, `password`, `access_type`) values";
		User newUser = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();
			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getAccessType());
			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) added", rows));
			log.trace("Getting result set...");
			if (rows == 0) {
				log.error("Creating user failed");
				throw new DAOException("Creating user failed");
			} else {
				resultSet = statement.getGeneratedKeys();

				if (resultSet.next()) {
					log.trace("Creating User to return...");
					newUser = new User(resultSet.getInt(1), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getAccessType());
				}
			}
		} catch (SQLException e) {
			log.error("Creating user failed");
			throw new DAOException("Creating user failed", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed");
			} catch (SQLException e) {
				log.error("Result set can't be closed", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed");
			} catch (SQLException e) {
				log.error("Connection can't be closed", e);
			}
		}
		log.trace("Returning User...");
		log.info(newUser + " is added to database");
		return newUser;
	}

	@Override
	public List<User> readAll() throws DAOException {
		log.info("Getting all users from database...");
		String sqlQuery = "select * from user";
		List<User> userList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();
			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			log.trace("Getting result set...");
			resultSet = statement.executeQuery();
			log.trace("Creating list of users to return...");
			while (resultSet.next()) {
				userList.add(new User(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("access_type")));
			}
		} catch (SQLException e) {
			log.error("Getting list of users failed");
			throw new DAOException("Getting list of users failed", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed");
			} catch (SQLException e) {
				log.error("Result set can't be closed", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed");
			} catch (SQLException e) {
				log.error("Connection can't be closed", e);
			}
		}
		log.trace("Returning list of users...");
		return userList;
	}

	@Override
	public User readByID(int id) throws DAOException {
		log.info("Getting user by id from database...");
		String sqlQuery = "select * from user by id";
		User user = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();
			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, id);
			log.trace("Getting result set...");
			resultSet = statement.executeQuery();
			log.trace("Creating User to return...");
			while (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("access_type"));
			}
		} catch (SQLException e) {
			log.error("Getting user by id failed");
			throw new DAOException("Getting user by id failed", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed");
			} catch (SQLException e) {
				log.error("Result set can't be closed", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed");
			} catch (SQLException e) {
				log.error("Connection can't be closed", e);
			}
		}
		log.trace("Returning User...");
		log.info(user + " is getted from database!");
		return user;
	}

	public User readByEmail(String email) throws DAOException {
		log.info("Getting user by email from database...");
		String sqlQuery = "select * from user by email";
		User user = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();
			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, email);
			log.trace("Getting result set...");
			resultSet = statement.executeQuery();
			log.trace("Creating User to return...");
			while (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("access_type"));
			}
		} catch (SQLException e) {
			log.error("Getting user by email failed");
			throw new DAOException("Getting user by email failed", e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				log.trace("Result set is closed");
			} catch (SQLException e) {
				log.error("Result set can't be closed", e);
			}
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed");
			} catch (SQLException e) {
				log.error("Connection can't be closed", e);
			}
		}
		log.trace("Returning User...");
		log.info(user + " is getted from database");
		return user;
	}

	@Override
	public boolean updateByID(User user)
			throws DAOException {
		log.info("Updating user by id in database...");
		String sqlQuery = "update user set first_name = ?, last_name = ?, email = ?, password = ?, access_type = ? where id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();
			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getAccessType());
			statement.setInt(6, user.getId());
			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) updated!", rows));

			if (rows == 0) {
				log.error("Updating user failed");
				throw new DAOException("Updating user failed");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Updating user failed");
			throw new DAOException("Updating user failed", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed");
			} catch (SQLException e) {
				log.error("Connection can't be closed", e);
			}
		}
		if (result == false) {
			log.info("Updating user failed");
		} else {
			log.trace("Returning result...");
			log.info("User with ID#" + user.getId() + " is updated in database");
		}
		return result;
	}

	public boolean updateByEmail(User user)
			throws DAOException {
		log.info("Updating user by email in database...");
		String sqlQuery = "update user set first_name = ?, last_name = ?, password = ?, access_type = ? where email = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();
			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getAccessType());
			statement.setString(5, user.getEmail());
			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) updated", rows));
			
			if (rows == 0) {
				log.error("Updating user failed");
				throw new DAOException("Updating user failed");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Updating user failed");
			throw new DAOException("Updating user failed", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed");
			} catch (SQLException e) {
				log.error("Connection can't be closed", e);
			}
		}
		if (result == false) {
			log.info("Updating user failed");
		} else {
			log.trace("Returning result...");
			log.info("User with email: " + user.getEmail() + " is updated in database");
		}
		return result;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		log.info("Deleting user by id from database...");
		String sqlQuery = "delete from user where id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;

		try {
			log.trace("Opening connection...");
			connection = DAOFactory.getInstance().getConnection();
			log.trace("Creating prepared statement...");
			statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, id);
			log.trace("Executing database update...");
			int rows = statement.executeUpdate();
			log.trace(String.format("%d row(s) deleted", rows));
			
			if (rows == 0) {
				log.error("Deleting user failed");
				throw new DAOException("Deleting user failed");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			log.error("Deleting user failed");
			throw new DAOException("Deleting user failed", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				log.trace("Prepared statement is closed");
			} catch (SQLException e) {
				log.error("Prepared statement can't be closed", e);
			}
			try {
				if (connection != null) {
					connection.close();
				}
				log.trace("Connection is closed");
			} catch (SQLException e) {
				log.error("Connection can't be closed", e);
			}
		}
		if (result == false) {
			log.info("Deleting user failed");
		} else {
			log.trace("Returning result...");
			log.info("User with ID#" + id + " is deleted from database");
		}
		return result;
	}
}