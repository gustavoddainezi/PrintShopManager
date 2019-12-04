
package database;

public class Admin {
    String[] nomeTabela = {"USUARIOS", "CLIENTES", "TELEFONESCLIENTES", "FUNCIONARIOS", "TELEFONESFUNCIONARIOS", "TELEFONESFORNECEDORES", "PRODUTOSFORNECIDOS", "FORNECEDORES"};

    public Admin() {
        ManipularBancoDados mdb = ManipularBancoDados.getInstancia();
        if (mdb.executarAcao("alter table TELEFONESCLIENTES drop foreign key IDCLIENTEFKTELEFONE")
                && mdb.executarAcao("alter table TELEFONESFUNCIONARIOS drop foreign key IDFUNCIONARIOFKTELEFONE")
                && mdb.executarAcao("alter table TELEFONESFORNECEDORES drop foreign key IDFORNECEDORFKTELEFONE")
                && mdb.executarAcao("alter table PRODUTOSFORNECIDOS drop foreign key IDFORNECEDORFKPRODUTOSFORNECIDOS")
            )
            System.out.println("Dependências excluídas com sucesso");
        for (int i = 0; i < this.nomeTabela.length; i++) {
            if (mdb.executarAcao("drop table " + this.nomeTabela[i]))
                System.out.println("Tabela "+this.nomeTabela[i]+" foi excluída.");
        }
    }
    
}
