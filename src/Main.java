import dao.*;
import entities.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Criar instâncias dos DAOs
            ProjetoDAO projetoDAO = new ProjetoDAO();
            EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
            OperarioDAO operarioDAO = new OperarioDAO();
            EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
            MaterialDAO materialDAO = new MaterialDAO();

            // Inserir um novo projeto
            Projeto projeto = new Projeto();
            projeto.setNomeProjeto("Construção do Edifício A");
            projeto.setLocal("São Paulo");
            projeto.setDataInicio(Date.valueOf("2024-01-01"));
            projeto.setDataTermino(Date.valueOf("2024-12-31"));
            projetoDAO.inserir(projeto);
            System.out.println("Projeto inserido com sucesso!");

            // Inserir um novo engenheiro
            Engenheiro engenheiro = new Engenheiro();
            engenheiro.setNomeEngenheiro("Carlos Silva");
            engenheiro.setEspecialidade("Estruturas");
            engenheiroDAO.inserir(engenheiro);
            System.out.println("Engenheiro inserido com sucesso!");

            // Inserir um novo operário
            Operario operario = new Operario();
            operario.setNomeOperario("João Pereira");
            operario.setFuncao("Pedreiro");
            operarioDAO.inserir(operario);
            System.out.println("Operário inserido com sucesso!");

            // Inserir um novo equipamento
            Equipamento equipamento = new Equipamento();
            equipamento.setNomeEquipamento("Betoneira");
            equipamento.setTipo("Máquina");
            equipamentoDAO.inserir(equipamento);
            System.out.println("Equipamento inserido com sucesso!");

            // Inserir um novo material
            Material material = new Material();
            material.setNomeMaterial("Cimento");
            material.setQuantidade(1000);
            materialDAO.inserir(material);
            System.out.println("Material inserido com sucesso!");

            // Atualizar um projeto
            projeto.setNomeProjeto("Construção do Edifício B");
            projetoDAO.atualizar(projeto);
            System.out.println("Projeto atualizado com sucesso!");

            // Listar todos os projetos
            List<Projeto> projetos = projetoDAO.listarTodos();
            System.out.println("Lista de Projetos:");
            for (Projeto p : projetos) {
                System.out.println(p);
            }

            // Listar todos os engenheiros
            List<Engenheiro> engenheiros = engenheiroDAO.listarTodos();
            System.out.println("Lista de Engenheiros:");
            for (Engenheiro e : engenheiros) {
                System.out.println(e);
            }

            // Listar todos os operários
            List<Operario> operarios = operarioDAO.listarTodos();
            System.out.println("Lista de Operários:");
            for (Operario o : operarios) {
                System.out.println(o);
            }

            // Listar todos os equipamentos
            List<Equipamento> equipamentos = equipamentoDAO.listarTodos();
            System.out.println("Lista de Equipamentos:");
            for (Equipamento eq : equipamentos) {
                System.out.println(eq);
            }

            // Listar todos os materiais
            List<Material> materiais = materialDAO.listarTodos();
            System.out.println("Lista de Materiais:");
            for (Material m : materiais) {
                System.out.println(m);
            }

            // Listar engenheiros alocados em um projeto específico (exemplo com ID 1)
            List<Engenheiro> engenheirosNoProjeto = projetoDAO.listarEngenheirosPorProjeto(1);
            System.out.println("Engenheiros no Projeto 1:");
            for (Engenheiro e : engenheirosNoProjeto) {
                System.out.println(e);
            }

            // Listar operários alocados em um projeto específico (exemplo com ID 1)
            List<Operario> operariosNoProjeto = projetoDAO.listarOperariosPorProjeto(1);
            System.out.println("Operários no Projeto 1:");
            for (Operario o : operariosNoProjeto) {
                System.out.println(o);
            }

            // Listar equipamentos utilizados em um projeto específico (exemplo com ID 1)
            List<Equipamento> equipamentosNoProjeto = projetoDAO.listarEquipamentosPorProjeto(1);
            System.out.println("Equipamentos no Projeto 1:");
            for (Equipamento eq : equipamentosNoProjeto) {
                System.out.println(eq);
            }

            // Listar materiais consumidos em um projeto específico (exemplo com ID 1)
            List<Material> materiaisNoProjeto = projetoDAO.listarMateriaisPorProjeto(1);
            System.out.println("Materiais no Projeto 1:");
            for (Material m : materiaisNoProjeto) {
                System.out.println(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
