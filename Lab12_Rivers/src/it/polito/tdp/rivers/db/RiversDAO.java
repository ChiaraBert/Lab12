package it.polito.tdp.rivers.db;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class RiversDAO {
	
	
	
	

	public List<River> getAllRivers() {
		final String sql = "SELECT id, name FROM river";

		List<River> rivers= new LinkedList<River>();
		

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException();
		}

		return rivers;
	}

		
	public LinkedList<Flow> getFlows(River r) {
		final String sql = "SELECT id, day, flow, river FROM flow WHERE river=? ORDER BY day ";

		LinkedList<Flow> flows = new LinkedList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flows.add(new Flow(res.getInt("id"), res.getTimestamp("day").toLocalDateTime(), res.getDouble("flow"),r));
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException();
		}

		return flows;
	}

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();

		List<River> rivers = dao.getAllRivers();
		System.out.println(rivers);

		//List<Flow> flows = dao.getFlows(rivers);
		//System.out.format("Loaded %d flows\n", flows.size());
		// System.out.println(flows) ;
	}

}
