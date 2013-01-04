package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.Exame;
import labbios.dto.Solicitacao;

public class ExameDAO extends DatabaseUtil {

	SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	CadastroDeExameDAO cadastroDeExameDAO = new CadastroDeExameDAO();
	TabelaPrecosDAO tabelaDePrecosDAO = new TabelaPrecosDAO();
	StatusDAO statusDAO = new StatusDAO();

	public boolean adicionarExame(Exame exame) throws ClassNotFoundException,
			SQLException {
		boolean retorno = false;

		PreparedStatement ps = getPreparedStatement("Insert into EXAME set STATUS_ID=?,"
				+ " SOL_ID=?,"
				+ " CAD_EXAME_ID=?,"
				+ " EXAME_DT_REALIZACAO=?,"
				+ " EXAME_CATEGORIA_IPE=?, " + " TAB_PRECOS_ID=?");

		ps.setInt(1, exame.getSTATUS().getSTATUS_ID());
		ps.setInt(2, exame.getSOLICITACAO().getSOL_ID());
		ps.setInt(3, exame.getCAD_EXAME().getCAD_EXAME_ID());
		ps.setDate(4, exame.getEXAME_DT_REALIZACAO());
		ps.setString(5, String.valueOf(exame.getEXAME_CATEGORIA_IP()));
		ps.setInt(6, exame.getEXAME_VALOR().getTAB_PRECOS_ID());

		retorno = ps.execute();
		ps.close();

		return retorno;
	}

	public boolean editarExame(Exame exame) throws ClassNotFoundException,
			SQLException {
		boolean retorno = false;

		PreparedStatement ps = getPreparedStatement("Update EXAME set STATUS_ID=?,"
				+ " SOLICITACAO_ID=?,"
				+ " CAD_EXAME_ID=?,"
				+ " EXAME_DT_REALIZACAO=?,"
				+ " EXAME_CATEGORIA_IPE=?, "
				+ " TAB_PRECOS_ID=? " + " where EXAME_ID=?");

		ps.setInt(1, exame.getSTATUS().getSTATUS_ID());
		ps.setInt(2, exame.getSOLICITACAO().getSOL_ID());
		ps.setInt(3, exame.getCAD_EXAME().getCAD_EXAME_ID());
		ps.setDate(4, exame.getEXAME_DT_REALIZACAO());
		ps.setString(5, String.valueOf(exame.getEXAME_CATEGORIA_IP()));
		ps.setInt(6, exame.getEXAME_VALOR().getTAB_PRECOS_ID());
		ps.setInt(7, exame.getEXAME_ID());

		retorno = ps.execute();
		ps.close();

		return retorno;
	}

	public List<Exame> listarExames() throws ClassNotFoundException,
			SQLException {
		ResultSet rs = getStatement().executeQuery("Select * from EXAME");
		return popularListaDeExames(rs);

	}

	public Exame buscarExamePorID(int exameID) throws ClassNotFoundException,
			SQLException {
		PreparedStatement ps = getPreparedStatement("Select * from EXAME where EXAME_ID=?");
		ps.setInt(1, exameID);
		ResultSet rs = ps.executeQuery();

		return popularExame(rs);

	}

	public List<Exame> recuperarExamesPorSolicitacao(Solicitacao solicitacao)
			throws ClassNotFoundException, SQLException {
		ResultSet rs = getStatement().executeQuery(
				"Select * from EXAME where SOL_ID="
						+ solicitacao.getSOL_ID());
		List<Exame> listaExames = new LinkedList<Exame>();
		while (rs.next()) {
			listaExames.add(popularExame(rs));
		}

		return listaExames;
	}

	public List<Exame> recuperarExamesPendentes() throws SQLException,
			ClassNotFoundException {
		int IdExamePendente = statusDAO.recuperarStatusIdPorNome("PENDENTE");
		ResultSet rs = getStatement().executeQuery(
				"Select * from EXAME where STATUS_ID=" + IdExamePendente);

		return popularListaDeExames(rs);
	}

	public List<Exame> recuperarExamesEmAndamento() throws SQLException,
			ClassNotFoundException {
		int IdExameEmAndamento = statusDAO
				.recuperarStatusIdPorNome("ANDAMENTO");
		ResultSet rs = getStatement().executeQuery(
				"Select * from EXAME where STATUS_ID=" + IdExameEmAndamento);

		return popularListaDeExames(rs);
	}

	public List<Exame> recuperarExamesFinalizados() throws SQLException,
			ClassNotFoundException {
		int IdExameFinalizado = statusDAO
				.recuperarStatusIdPorNome("FINALIZADO");
		ResultSet rs = getStatement().executeQuery(
				"Select * from EXAME where STATUS_ID=" + IdExameFinalizado);

		return popularListaDeExames(rs);
	}

	public Exame popularExame(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		Exame exame = new Exame();
		exame.setEXAME_ID(rs.getInt("EXAME_ID"));
		exame.setSTATUS(statusDAO.procurarStatusPorID(rs.getInt("STATUS_ID")));
		exame.setSOLICITACAO(solicitacaoDAO.procurarSolicitacaoPorID(rs
				.getInt("SOL_ID")));
		exame.setCAD_EXAME(cadastroDeExameDAO.buscarCadastroDeExamePorID(rs
				.getInt("CAD_EXAME_ID")));
		exame.setEXAME_DT_REALIZACAO(rs.getDate("EXAME_DT_REALIZACAO"));
		exame.setEXAME_CATEGORIA_IP(rs.getString("EXAME_CATEGORIA_IPE").charAt(
				0));
		exame.setEXAME_VALOR(tabelaDePrecosDAO.procurarTabelaDePrecosPorID(rs
				.getInt("TAB_PRECOS_ID")));

		return exame;
	}

	private List<Exame> popularListaDeExames(ResultSet rs) throws SQLException,
			ClassNotFoundException {
		List<Exame> listaExames = new LinkedList<Exame>();
		while (rs.next()) {
			listaExames.add(popularExame(rs));
		}

		return listaExames;
	}

}
