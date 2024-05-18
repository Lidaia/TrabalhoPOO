package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.PassageiroDAO;
import DAO.MotoristaDAO;
import DAO.ViagemDAO;
import entidades.Passageiro;
import entidades.Motorista;
import entidades.Viagem;

class MainFrame extends JFrame {
    private PassageiroDAO passageiroDAO;
    private MotoristaDAO motoristaDAO;
    private ViagemDAO viagemDAO;

    public MainFrame() {
        passageiroDAO = new PassageiroDAO();
        motoristaDAO = new MotoristaDAO();
        viagemDAO = new ViagemDAO();
        
        setTitle("Sistema de Cadastro e Viagem");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Painel de Cadastro de Pessoa
        JPanel panelPessoa = new JPanel(new GridLayout(8, 2));
        panelPessoa.add(new JLabel("CPF:"));
        JTextField cpfFieldPessoa = new JTextField();
        panelPessoa.add(cpfFieldPessoa);

        panelPessoa.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        panelPessoa.add(nomeField);

        panelPessoa.add(new JLabel("Endereço:"));
        JTextField enderecoField = new JTextField();
        panelPessoa.add(enderecoField);

        panelPessoa.add(new JLabel("Telefone:"));
        JTextField telefoneField = new JTextField();
        panelPessoa.add(telefoneField);

        panelPessoa.add(new JLabel("Sexo:"));
        JTextField sexoField = new JTextField();
        panelPessoa.add(sexoField);

        panelPessoa.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        panelPessoa.add(emailField);

        panelPessoa.add(new JLabel("Tipo (Passageiro/Motorista):"));
        JTextField tipoField = new JTextField();
        panelPessoa.add(tipoField);

        JButton cadastrarPessoaButton = new JButton("Cadastrar Pessoa");
        cadastrarPessoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long cpf = Long.parseLong(cpfFieldPessoa.getText());
                String nome = nomeField.getText();
                String endereco = enderecoField.getText();
                int telefone = Integer.parseInt(telefoneField.getText());
                char sexo = sexoField.getText().charAt(0);
                String email = emailField.getText();
                String tipo = tipoField.getText();

                if (tipo.equalsIgnoreCase("passageiro")) {
                    JPanel panelPassageiro = new JPanel(new GridLayout(3, 2));
                    panelPassageiro.add(new JLabel("Cartão de Crédito:"));
                    JTextField cartaoField = new JTextField();
                    panelPassageiro.add(cartaoField);

                    panelPassageiro.add(new JLabel("Bandeira do Cartão:"));
                    JTextField bandeiraField = new JTextField();
                    panelPassageiro.add(bandeiraField);

                    panelPassageiro.add(new JLabel("Cidade de Origem:"));
                    JTextField cidadeField = new JTextField();
                    panelPassageiro.add(cidadeField);

                    int result = JOptionPane.showConfirmDialog(null, panelPassageiro, "Cadastro de Passageiro", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        Passageiro passageiro = new Passageiro();
                        passageiro.setCpf(cpf);
                        passageiro.setCartaoCredito(cartaoField.getText());
                        passageiro.setBandeiraCartao(bandeiraField.getText());
                        passageiro.setCidadeOrigem(cidadeField.getText());

                        passageiroDAO.adicionarPassageiro(passageiro);
                        JOptionPane.showMessageDialog(MainFrame.this, "Passageiro cadastrado com sucesso!");
                    }
                } else if (tipo.equalsIgnoreCase("motorista")) {
                    JPanel panelMotorista = new JPanel(new GridLayout(4, 2));
                    panelMotorista.add(new JLabel("CNH:"));
                    JTextField cnhField = new JTextField();
                    panelMotorista.add(cnhField);

                    panelMotorista.add(new JLabel("Banco:"));
                    JTextField bancoField = new JTextField();
                    panelMotorista.add(bancoField);

                    panelMotorista.add(new JLabel("Agência:"));
                    JTextField agenciaField = new JTextField();
                    panelMotorista.add(agenciaField);

                    panelMotorista.add(new JLabel("Conta:"));
                    JTextField contaField = new JTextField();
                    panelMotorista.add(contaField);

                    int result = JOptionPane.showConfirmDialog(null, panelMotorista, "Cadastro de Motorista", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        Motorista motorista = new Motorista();
                        motorista.setCpf(cpf);
                        motorista.setCnh(cnhField.getText());
                        motorista.setBanco(Integer.parseInt(bancoField.getText()));
                        motorista.setAgencia(Integer.parseInt(agenciaField.getText()));
                        motorista.setConta(Integer.parseInt(contaField.getText()));

                        motoristaDAO.adicionarMotorista(motorista);
                        JOptionPane.showMessageDialog(MainFrame.this, "Motorista cadastrado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Tipo inválido. Digite 'Passageiro' ou 'Motorista'.");
                }
            }
        });
        panelPessoa.add(cadastrarPessoaButton);

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
            }
        });
        panelViagem.add(solicitarViagemButton);

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
