package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entidades.Passageiro;
import entidades.Motorista;
import entidades.Pessoas;
import entidades.Viagem;
import DAO.PassageiroDAO;
import DAO.MotoristaDAO;
import DAO.PessoasDAO;
import DAO.ViagemDAO;
import java.util.List;
import java.util.ArrayList;

class MainFrame extends JFrame {

    private PassageiroDAO passageiroDAO;
    private MotoristaDAO motoristaDAO;
    private PessoasDAO pessoasDAO;
    private ViagemDAO viagemDAO;

    private JComboBox<String> sexoComboBox;

    public MainFrame() {
        passageiroDAO = new PassageiroDAO();
        motoristaDAO = new MotoristaDAO();
        pessoasDAO = new PessoasDAO();
        viagemDAO = new ViagemDAO();

        setTitle("Sistema de Cadastro e Viagem");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel de Cadastro de Pessoa
        JPanel panelPessoa = new JPanel(new BorderLayout());
        JPanel commonPanel = new JPanel(new GridLayout(7, 2));
        commonPanel.add(new JLabel("CPF:"));
        JTextField cpfFieldPessoa = new JTextField();
        commonPanel.add(cpfFieldPessoa);

        commonPanel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        commonPanel.add(nomeField);

        commonPanel.add(new JLabel("Endereço:"));
        JTextField enderecoField = new JTextField();
        commonPanel.add(enderecoField);

        commonPanel.add(new JLabel("Telefone:"));
        JTextField telefoneField = new JTextField();
        commonPanel.add(telefoneField);

        commonPanel.add(new JLabel("Sexo:"));
        sexoComboBox = new JComboBox<>(new String[]{"", "F", "M"});
        commonPanel.add(sexoComboBox);

        commonPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        commonPanel.add(emailField);

        commonPanel.add(new JLabel("Tipo:"));
        String[] tipos = {"Selecione", "Passageiro", "Motorista"};
        JComboBox<String> tipoComboBox = new JComboBox<>(tipos);
        commonPanel.add(tipoComboBox);

        JPanel especificoPanel = new JPanel();
        panelPessoa.add(commonPanel, BorderLayout.NORTH);
        panelPessoa.add(especificoPanel, BorderLayout.CENTER);

        tipoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                especificoPanel.removeAll();
                if (tipoComboBox.getSelectedItem().equals("Passageiro")) {
                    especificoPanel.setLayout(new GridLayout(4, 2));
                    especificoPanel.add(new JLabel("Cartão de Crédito:"));
                    JTextField cartaoField = new JTextField();
                    especificoPanel.add(cartaoField);

                    especificoPanel.add(new JLabel("Bandeira do Cartão:"));
                    JTextField bandeiraField = new JTextField();
                    especificoPanel.add(bandeiraField);

                    especificoPanel.add(new JLabel("Cidade de Origem:"));
                    JTextField cidadeField = new JTextField();
                    especificoPanel.add(cidadeField);

                    especificoPanel.revalidate();
                    especificoPanel.repaint();
                } else if (tipoComboBox.getSelectedItem().equals("Motorista")) {
                    especificoPanel.setLayout(new GridLayout(4, 2));
                    especificoPanel.add(new JLabel("CNH:"));
                    JTextField cnhField = new JTextField();
                    especificoPanel.add(cnhField);

                    especificoPanel.add(new JLabel("Banco:"));
                    JTextField bancoField = new JTextField();
                    especificoPanel.add(bancoField);

                    especificoPanel.add(new JLabel("Agência:"));
                    JTextField agenciaField = new JTextField();
                    especificoPanel.add(agenciaField);

                    especificoPanel.add(new JLabel("Conta:"));
                    JTextField contaField = new JTextField();
                    especificoPanel.add(contaField);

                    especificoPanel.revalidate();
                    especificoPanel.repaint();
                }
            }
        });

        JButton cadastrarPessoaButton = new JButton("Cadastrar Pessoa");
        cadastrarPessoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tipoComboBox.getSelectedItem().equals("Selecione")) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Por favor, selecione um tipo válido (Passageiro ou Motorista).");
                    return;
                }

                long cpf = Long.parseLong(cpfFieldPessoa.getText());
                String nome = nomeField.getText();
                String endereco = enderecoField.getText();
                int telefone = Integer.parseInt(telefoneField.getText());
                char sexo = sexoComboBox.getSelectedItem().toString().charAt(0);
                String email = emailField.getText();
                String tipo = (String) tipoComboBox.getSelectedItem();

                Pessoas pessoa = new Pessoas();
                pessoa.setCpf(cpf);
                pessoa.setNome(nome);
                pessoa.setEndereco(endereco);
                pessoa.setTelefone(telefone);
                pessoa.setSexo(sexo);
                pessoa.setEmail(email);

                pessoasDAO.adicionarPessoa(pessoa);

                if (tipo.equals("Passageiro")) {
                    JTextField cartaoField = (JTextField) especificoPanel.getComponent(1);
                    JTextField bandeiraField = (JTextField) especificoPanel.getComponent(3);
                    JTextField cidadeField = (JTextField) especificoPanel.getComponent(5);
                    Passageiro passageiro = new Passageiro();
                    passageiro.setCpfPassag(cpf);
                    passageiro.setCartaoCred(cartaoField.getText());
                    passageiro.setBandeiraCartao(bandeiraField.getText());
                    passageiro.setCidadeOrig(cidadeField.getText());

                    passageiroDAO.adicionarPassageiro(passageiro);
                    JOptionPane.showMessageDialog(MainFrame.this, "Passageiro cadastrado com sucesso!");
                } else if (tipo.equals("Motorista")) {
                    JTextField cnhField = (JTextField) especificoPanel.getComponent(1);
                    JTextField bancoField = (JTextField) especificoPanel.getComponent(3);
                    JTextField agenciaField = (JTextField) especificoPanel.getComponent(5);
                    JTextField contaField = (JTextField) especificoPanel.getComponent(7);

                    Motorista motorista = new Motorista();
                    motorista.setCpfMotorista(cpf);
                    motorista.setCnh(cnhField.getText());
                    motorista.setBancoMot(Integer.parseInt(bancoField.getText()));
                    motorista.setAgenciaMot(Integer.parseInt(agenciaField.getText()));
                    motorista.setContaMot(Integer.parseInt(contaField.getText()));

                    motoristaDAO.adicionarMotorista(motorista);
                    JOptionPane.showMessageDialog(MainFrame.this, "Motorista cadastrado com sucesso!");
                }
            }
        });
        panelPessoa.add(cadastrarPessoaButton, BorderLayout.SOUTH);

        // Painel de Solicitação de Viagem
        JPanel panelViagem = new JPanel(new GridLayout(5, 2));
        panelViagem.add(new JLabel("Forma de Pagamento:"));
        JTextField formaPagtoField = new JTextField();
        panelViagem.add(formaPagtoField);

        panelViagem.add(new JLabel("Valor do Pagamento:"));
        JTextField valorPagtoField = new JTextField();
        panelViagem.add(valorPagtoField);

        panelViagem.add(new JLabel("Local de Origem:"));
        JTextField localOrigField = new JTextField();
        panelViagem.add(localOrigField);

        panelViagem.add(new JLabel("Local de Destino:"));
        JTextField localDestField = new JTextField();
        panelViagem.add(localDestField);

        JButton solicitarViagemButton = new JButton("Solicitar Viagem");
        solicitarViagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formaPagto = formaPagtoField.getText();
                double valorPagto = Double.parseDouble(valorPagtoField.getText());
                String localOrig = localOrigField.getText();
                String localDest = localDestField.getText();

                Viagem viagem = new Viagem();
                viagem.setFormaPagto(formaPagto);
                viagem.setValorPagto(valorPagto);
                viagem.setLocalOrigViag(localOrig);
                viagem.setLocalDestViag(localDest);

                viagemDAO.adicionarViagem(viagem);

                JOptionPane.showMessageDialog(MainFrame.this, "Procurando um motorista perto...");

                // Exibir o nome do último motorista cadastrado
                JOptionPane.showMessageDialog(MainFrame.this, "Motorista a caminho ");
            }
        });
        panelViagem.add(solicitarViagemButton);

        JButton cancelarCorridaButton = new JButton("Cancelar Corrida");
        cancelarCorridaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmacao = JOptionPane.showConfirmDialog(MainFrame.this, "Tem certeza que deseja cancelar a corrida?", "Cancelar Corrida", JOptionPane.YES_NO_OPTION);
                if (confirmacao == JOptionPane.YES_OPTION) {
                    // Lógica para cancelar a corrida
                    // Aqui você pode chamar um método para cancelar a corrida no banco de dados
                    // Exemplo: viagemDAO.cancelarViagem(cpfPassViag, cpfMotViag, placaVeicViag, dtHoraInicio);

                    JOptionPane.showMessageDialog(MainFrame.this, "Corrida cancelada com sucesso!");
                }
            }
        });
        panelViagem.add(cancelarCorridaButton);

        // Adicionar botão para listar passageiros e motoristas
        JButton listarPassageirosMotoristasButton = new JButton("Listar Passageiros e Motoristas");
        listarPassageirosMotoristasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Listar passageiros e motoristas
                List<Passageiro> passageiros = passageiroDAO.recuperarPassageiros();
                List<Motorista> motoristas = motoristaDAO.recuperarMotoristas();

                StringBuilder message = new StringBuilder("Passageiros:\n");
                for (Passageiro passageiro : passageiros) {
                    message.append("- ").append(passageiro.getCpfPassag()).append("\n");
                }
                message.append("\nMotoristas:\n");
                for (Motorista motorista : motoristas) {
                    message.append("- ").append(motorista.getCpfMotorista()).append("\n");
                }

                JOptionPane.showMessageDialog(MainFrame.this, message.toString());
            }
        });
        panelViagem.add(listarPassageirosMotoristasButton);

        // Adicionar botão para mostrar histórico de corridas
        JButton historicoCorridasButton = new JButton("Histórico de Corridas");
        historicoCorridasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar histórico de corridas
                List<Viagem> viagens = viagemDAO.listarViagens();

                StringBuilder message = new StringBuilder("Histórico de Corridas:\n");
                for (Viagem viagem : viagens) {
                    message.append("- ").append(viagem.getLocalOrigViag()).append(" para ")
                            .append(viagem.getLocalDestViag()).append(", Valor: ").append(viagem.getValorPagto())
                            .append(", Forma de Pagamento: ").append(viagem.getFormaPagto()).append("\n");
                }

                JOptionPane.showMessageDialog(MainFrame.this, message.toString());
            }
        });
        panelViagem.add(historicoCorridasButton);

        // Adicionando os painéis às abas
        tabbedPane.addTab("Cadastrar Pessoa", panelPessoa);
        tabbedPane.addTab("Solicitar Viagem", panelViagem);

        add(tabbedPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
        
