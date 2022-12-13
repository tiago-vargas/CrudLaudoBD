package br.com.frota.util;

import br.com.frota.DAO.EspecialidadeDAO;
import br.com.frota.model.Especialidade;
import br.com.frota.servico.ServicoEspecialidade;

import java.sql.SQLException;
import java.util.List;

public class TesteEspecialidade {
    static final EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();

    static final ServicoEspecialidade servicoEspecialidade = new ServicoEspecialidade();

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
        especialidadeDAO.update(especialidade);
        especialidade = especialidadeDAO.findById(id);
        System.out.println(especialidade);

        //Select all
        List<Especialidade> especialidades = especialidadeDAO.selectAll();
        especialidades.forEach(System.out::println);

        //Delete
        especialidadeDAO.delete(id);
        especialidadeDAO.selectAll().forEach(System.out::println);
    }
}
