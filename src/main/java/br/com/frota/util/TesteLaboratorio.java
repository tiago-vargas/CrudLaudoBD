package br.com.frota.util;

import br.com.frota.model.Laboratorio;
import br.com.frota.servico.ServicoLaboratorio;

import java.sql.SQLException;

public class TesteLaboratorio {
    static final ServicoLaboratorio servicoLaboratorio = new ServicoLaboratorio();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(servicoLaboratorio.contar());

        //salvar
        Laboratorio laboratorio = new Laboratorio(
                "D-1", "CNES-1", "CNPJ-1", "CRBM-1", "NF-1");
        servicoLaboratorio.salvar(laboratorio);

        //buscar por ID
        long id = laboratorio.getId();
        laboratorio = servicoLaboratorio.buscarPorId(id);
        System.out.println(laboratorio);

        //Update
        laboratorio.setDescricao("D-2");
        laboratorio.setCnes("CNES-2");
        laboratorio.setCnpj("CNPJ-2");
        laboratorio.setCrbm("CRBM-2");
        laboratorio.setNomeFantasia("NF-2");
        servicoLaboratorio.update(laboratorio);
        laboratorio = servicoLaboratorio.buscarPorId(id);
        System.out.println(laboratorio);

        //Select all
        servicoLaboratorio.buscarTodos().forEach(System.out::println);

        //Delete
        servicoLaboratorio.remover(id);
        servicoLaboratorio.buscarTodos().forEach(System.out::println);
    }
}
