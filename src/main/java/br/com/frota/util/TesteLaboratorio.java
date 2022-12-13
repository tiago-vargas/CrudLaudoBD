package br.com.frota.util;

import br.com.frota.DAO.LaboratorioDAO;
import br.com.frota.model.Laboratorio;
import br.com.frota.servico.ServicoLaboratorio;

import java.sql.SQLException;
import java.util.List;

public class TesteLaboratorio {
    static final LaboratorioDAO laboratorioDAO = new LaboratorioDAO();

    static final ServicoLaboratorio servicoLaboratorio = new ServicoLaboratorio();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(laboratorioDAO.count());

        //salvar
        Laboratorio laboratorio = new Laboratorio(
                "Descricao", "CNES", "CNPJ", "CRBM", "Nome Fantasia");
        servicoLaboratorio.salvar(laboratorio);
        long id = laboratorio.getId();

        //buscar por ID
        laboratorio = laboratorioDAO.findById(id);
        System.out.println(laboratorio);

        //Update
        laboratorio.setDescricao("Descricao-2");
        laboratorio.setCnes("CNES-2");
        laboratorio.setCnpj("CNPJ-2");
        laboratorio.setCrbm("CRBM-2");
        laboratorio.setNomeFantasia("Novo Nome Fantasia");
        laboratorioDAO.updateLaboratorio(laboratorio);
        laboratorio = laboratorioDAO.findById(id);
        System.out.println(laboratorio);

        //Select all
        List<Laboratorio> laboratorios = laboratorioDAO.selectAllLaboratorio();
        laboratorios.forEach(System.out::println);

        //Delete
        laboratorioDAO.deleteLaboratorio(id);
        laboratorioDAO.selectAllLaboratorio().forEach(System.out::println);
    }
}
