package br.com.frota.util;

import br.com.frota.DAO.EspecialidadeDAO;
import br.com.frota.model.Especialidade;
import br.com.frota.servico.ServicoEspecialidade;

import java.sql.SQLException;
import java.util.List;

public class TesteEspecialidade {
    static EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();

    static ServicoEspecialidade servicoEspecialidade = new ServicoEspecialidade();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(especialidadeDAO.count());

        //salvar
        Especialidade especialidade = new Especialidade("Descricao", "Observacao");
        servicoEspecialidade.salvar(especialidade);
        long id = especialidade.getId();

        //buscar por ID
        especialidade = especialidadeDAO.findById(id);
        System.out.println(especialidade);

        //Update
        especialidade.setDescricao("Descricao-2");
        especialidade.setObservacao("Observacao-2");
        especialidadeDAO.updateEspecialidade(especialidade);
        especialidade = especialidadeDAO.findById(id);
        System.out.println(especialidade);

        //Select all
        List<Especialidade> especialidades = especialidadeDAO.selectAllEspecialidade();
        especialidades.forEach(System.out::println);

        //Delete
        especialidadeDAO.deleteEspecialidade(id);
        especialidadeDAO.selectAllEspecialidade().forEach(System.out::println);
    }
}
