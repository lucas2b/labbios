package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.Solicitacao;

public class SolicitacaoDAO extends DatabaseUtil {

	PacienteDAO pacienteDAO = new PacienteDAO();
	MedicoDAO medicoDAO = new MedicoDAO();
	AnimalDAO animalDAO = new AnimalDAO();
	StatusDAO statusDAO = new StatusDAO();
	PacienteTemporarioDAO pacienteTemporarioDAO = new PacienteTemporarioDAO();

	public boolean adicionarSolicitacao(Solicitacao solicitacao)
			throws ClassNotFoundException, SQLException {
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into SOLICITACAO set PACIENTE_ID=?,"
				+ " SOL_DT_CRIACAO=?,"
				+ " STATUS_ID=?,"
				+ " MEDICO_ID=?,"
				+ " ANIMAL_ID=?,"
				+ " SOL_VALOR=?,"
				+ " PACIENTE_TEMPORARIO_ID=?,"
				+ " SOL_OBS=?,"
				+ " SOL_DESC_PERCENTUAL=?,"
				+ " SOL_DESC_DINHEIRO=?,"
				+ " SOL_FLAG_URGENTE=?," + " SOL_VALOR_PAGO=?");

		ps.setInt(1, solicitacao.getPACIENTE().getPACIENTE_ID());
		ps.setDate(2, new java.sql.Date(Calendar.getInstance()
				.getTimeInMillis()));
		ps.setInt(3, solicitacao.getSTATUS().getSTATUS_ID());
		ps.setInt(4, solicitacao.getMEDICO().getMEDICO_ID());
		ps.setInt(5, solicitacao.getANIMAL().getANIMAL_ID());
		ps.setDouble(6, solicitacao.getSOL_VALOR());
		ps.setInt(7, solicitacao.getPACIENTE_TEMPORARIO()
				.getPACIENTE_TEMPORARIO_ID());
		ps.setString(8, solicitacao.getSOL_OBS());
		ps.setDouble(9, solicitacao.getSOL_DESC_PERCENTUAL());
		ps.setDouble(10, solicitacao.getSOL_DESC_DINHEIRO());
		ps.setInt(11, solicitacao.getSOL_FLAG_URGENTE());
		ps.setDouble(12, solicitacao.getSOL_VALOR_PAGO());

		retorno = ps.execute();

		return retorno;
	}

	public boolean atualizarSolicitacao(Solicitacao solicitacao)
			throws SQLException, ClassNotFoundException {
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Update SOLICITACAO set PACIENTE_ID=?,"
				+ " SOL_DT_CRIACAO=?,"
				+ " STATUS_ID=?,"
				+ " MEDICO_ID=?,"
				+ " ANIMAL_ID=?,"
				+ " SOL_VALOR=?,"
				+ " PACIENTE_TEMPORARIO_ID=?,"
				+ " SOL_OBS=?,"
				+ " SOL_DESC_PERCENTUAL=?,"
				+ " SOL_DESC_DINHEIRO=?,"
				+ " SOL_FLAG_URGENTE=?,"
				+ " SOL_VALOR_PAGO=? "
				+ " where SOL_ID=?");

		ps.setInt(1, solicitacao.getPACIENTE().getPACIENTE_ID());
		ps.setDate(2, new java.sql.Date(Calendar.getInstance()
				.getTimeInMillis()));
		ps.setInt(3, solicitacao.getSTATUS().getSTATUS_ID());
		ps.setInt(4, solicitacao.getMEDICO().getMEDICO_ID());
		ps.setInt(5, solicitacao.getANIMAL().getANIMAL_ID());
		ps.setDouble(6, solicitacao.getSOL_VALOR());
		ps.setInt(7, solicitacao.getPACIENTE_TEMPORARIO()
				.getPACIENTE_TEMPORARIO_ID());
		ps.setString(8, solicitacao.getSOL_OBS());
		ps.setDouble(9, solicitacao.getSOL_DESC_PERCENTUAL());
		ps.setDouble(10, solicitacao.getSOL_DESC_DINHEIRO());
		ps.setInt(11, solicitacao.getSOL_FLAG_URGENTE());
		ps.setDouble(12, solicitacao.getSOL_VALOR_PAGO());
		ps.setInt(13, solicitacao.getSOL_ID());

		retorno = ps.execute();

		return retorno;
	}

	public List<Solicitacao> listarSolicitacoes()
			throws ClassNotFoundException, SQLException {
		ResultSet rs = getStatement().executeQuery("Select * from SOLICITACAO");
		List<Solicitacao> listaSolicitacoes = new LinkedList<Solicitacao>();

		while (rs.next()) {
			listaSolicitacoes.add(popularSolicitacao(rs));
		}

		return listaSolicitacoes;

	}

	public Solicitacao procurarSolicitacaoPorID(int solicitacaoID)
			throws ClassNotFoundException, SQLException {
		ResultSet rs = getStatement().executeQuery(
				"Select * from SOLICITACAO where SOL_ID=" +solicitacaoID);
		rs.next();
		return popularSolicitacao(rs);
	}

	public int recuperarUltimoID() throws ClassNotFoundException, SQLException {
		ResultSet rs = getStatement().executeQuery(
				"Select SOL_ID from SOLICITACAO");
		int i = 0;
		while (rs.next()) {
			i = rs.getInt("SOL_ID");
		}
		return i;
		// Retorna o último ID acrescido de 1 para fechar com a solicitação que
		// está sendo inserida no momento
	}

	public Solicitacao recuperarSolicitacao(Solicitacao solicitacao)
			throws ClassNotFoundException, SQLException {
		ResultSet rs = getStatement().executeQuery(
				"Select * from SOLICITACAO where SOL_ID="
						+ solicitacao.getSOL_ID());
		rs.next();
		return popularSolicitacao(rs);
	}

	public List<Solicitacao> recuperarSolicitacoesCadastradas()
			throws SQLException, ClassNotFoundException {
		int IdSolicitacaoCadastrada = statusDAO
				.recuperarStatusIdPorNome("CADASTRADO");
		ResultSet rs = getStatement().executeQuery(
				"Select * from SOLICITACAO where SOL_ID="
						+ IdSolicitacaoCadastrada);
		List<Solicitacao> listaSolicitacao = new LinkedList<Solicitacao>();

		while (rs.next()) {
			listaSolicitacao.add(popularSolicitacao(rs));
		}

		return listaSolicitacao;
	}

	public List<Solicitacao> recuperarSolicitacoesEmAndamento()
			throws SQLException, ClassNotFoundException {
		int IdSolicitacaoEmAndamento = statusDAO
				.recuperarStatusIdPorNome("ANDAMENTO");
		ResultSet rs = getStatement().executeQuery(
				"Select * from SOLICITACAO where SOL_ID="
						+ IdSolicitacaoEmAndamento);
		List<Solicitacao> listaSolicitacao = new LinkedList<Solicitacao>();

		while (rs.next()) {
			listaSolicitacao.add(popularSolicitacao(rs));
		}

		return listaSolicitacao;
	}

	public List<Solicitacao> recuperarSolicitacoesFinalizadas()
			throws SQLException, ClassNotFoundException {
		int IdSolicitacaoFinalizada = statusDAO
				.recuperarStatusIdPorNome("FINALIZADO");
		ResultSet rs = getStatement().executeQuery(
				"Select * from SOLICITACAO where SOL_ID="
						+ IdSolicitacaoFinalizada);
		List<Solicitacao> listaSolicitacao = new LinkedList<Solicitacao>();

		while (rs.next()) {
			listaSolicitacao.add(popularSolicitacao(rs));
		}

		return listaSolicitacao;
	}

	public List<Solicitacao> recuperarSolicitacoesEntregues()
			throws SQLException, ClassNotFoundException {
		int IdSolicitacaoEntregue = statusDAO
				.recuperarStatusIdPorNome("ENTREGUE");
		ResultSet rs = getStatement().executeQuery(
				"Select * from SOLICITACAO where SOL_ID="
						+ IdSolicitacaoEntregue);
		List<Solicitacao> listaSolicitacao = new LinkedList<Solicitacao>();

		while (rs.next()) {
			listaSolicitacao.add(popularSolicitacao(rs));
		}

		return listaSolicitacao;
	}

	public Solicitacao popularSolicitacao(ResultSet rs)
			throws ClassNotFoundException, SQLException {
		Solicitacao solicitacao = new Solicitacao();
		solicitacao.setSOL_ID(rs.getInt("SOL_ID"));
		solicitacao.setPACIENTE(pacienteDAO.procurarPacientePorID(rs
				.getInt("PACIENTE_ID")));
		solicitacao.setSOL_DT_CRIACAO(rs.getDate("SOL_DT_CRIACAO"));
		solicitacao.setSTATUS(statusDAO.procurarStatusPorID(rs
				.getInt("STATUS_ID")));
		solicitacao.setMEDICO(medicoDAO.procurarMedicoPorID(rs
				.getInt("MEDICO_ID")));
		solicitacao.setANIMAL(animalDAO.procurarAnimalPorID(rs
				.getInt("ANIMAL_ID")));
		solicitacao.setSOL_VALOR(rs.getDouble("SOL_VALOR"));
		solicitacao.setPACIENTE_TEMPORARIO(pacienteTemporarioDAO
				.procurarPacienteTemporarioPorID(rs
						.getInt("PACIENTE_TEMPORARIO_ID")));
		solicitacao.setSOL_OBS(rs.getString("SOL_OBS"));
		solicitacao.setSOL_DESC_PERCENTUAL(rs.getDouble("SOL_DESC_PERCENTUAL"));
		solicitacao.setSOL_DESC_DINHEIRO(rs.getDouble("SOL_DESC_DINHEIRO"));
		solicitacao.setSOL_FLAG_URGENTE(rs.getInt("SOL_FLAG_URGENTE"));
		solicitacao.setSOL_VALOR_PAGO(rs.getDouble("SOL_VALOR_PAGO"));

		return solicitacao;
	}

}
