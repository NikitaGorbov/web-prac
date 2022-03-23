package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Position;

public interface PositionDAO {
	void add(Position position) throws SQLException;
	List<Position> getAll();
	Position getById(int id);
	void update(Position position);
	void remove(Position position);
}
