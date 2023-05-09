package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Contato;
import model.ContatoDAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.DataBufferUShort;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Window.Type;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class ContatosFrame {

	private JFrame frmGerenciamentoDeContatos;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtIdade;
	private JScrollPane jScrollPane1;
	private JTable jTableMostraContatos;
	private JTextField txtBusca;
	private TableModel model;
	private JComboBox comboBox;
	ContatoDAO contatoDAO = new ContatoDAO();


	//limpar campos
	public void limparCamposTextos() {
		txtId.setText("");
		txtNome.setText("");
		txtEmail.setText("");
		txtIdade.setText("");
	}
	// Apresentar dados na Jtable

	public void apresentaDadosJTable() {		
		ArrayList<Contato> list = contatoDAO.getContatosList();

		DefaultTableModel model = (DefaultTableModel) jTableMostraContatos.getModel();
		Object[] row = new Object[4];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getId();
			row[1] = list.get(i).getNome();
			row[2] = list.get(i).getEmail();
			row[3] = list.get(i).getIdade();

			model.addRow(row);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContatosFrame window = new ContatosFrame();
					window.frmGerenciamentoDeContatos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContatosFrame() {
		initialize();
		apresentaDadosJTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// para obter o diretório/pasta da instalação do programa
		String url = System.getProperty("user.dir");
		frmGerenciamentoDeContatos = new JFrame();
		frmGerenciamentoDeContatos.setTitle("Gerenciamento de Contatos");
		frmGerenciamentoDeContatos.setBounds(100, 100, 650, 380);
		frmGerenciamentoDeContatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jScrollPane1 = new javax.swing.JScrollPane();

		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N

		txtIdade = new JTextField();
		txtIdade.setColumns(10);
		txtIdade.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//uso do V.O. para realizar a transição dos dados entre a camada de visão (view)
				//e a camada de negócios (model)
				Contato contato = new Contato(txtNome.getText(),
						txtEmail.getText(), Integer.parseInt(txtIdade.getText()));
				
				if (contatoDAO.inserir(contato)) {
					JOptionPane.showMessageDialog(null, "Contato inserido no sistema");
					DefaultTableModel model = (DefaultTableModel) jTableMostraContatos.getModel();
					model.setRowCount(0);
					apresentaDadosJTable();
					limparCamposTextos();
				} else
					JOptionPane.showMessageDialog(null, "Contato não inserido no sistema");
			}						
		});

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contatoDAO.atualizar(new Contato(Integer.parseInt(txtId.getText()), txtNome.getText(),
						txtEmail.getText(), Integer.parseInt(txtIdade.getText())))) {
					JOptionPane.showMessageDialog(null, "Contato atualizado no sistema");
					DefaultTableModel model = (DefaultTableModel) jTableMostraContatos.getModel();
					model.setRowCount(0);
					apresentaDadosJTable();
					limparCamposTextos();
				} else
					JOptionPane.showMessageDialog(null, "Contato não atualizado no sistema");

			}
		});

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contatoDAO.deletar(Integer.parseInt(txtId.getText()))) {
					JOptionPane.showMessageDialog(null, "Contato deletado no sistema");
					DefaultTableModel model = (DefaultTableModel) jTableMostraContatos.getModel();
					model.setRowCount(0);
					apresentaDadosJTable();
				} else
					JOptionPane.showMessageDialog(null, "Não foi possível deletar contato");

			}
		});

		JScrollPane scrollPane1 = new JScrollPane();

		txtBusca = new JTextField();
		txtBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String select = (String) comboBox.getSelectedItem();
					jTableMostraContatos.setModel(DbUtils.resultSetToTableModel(contatoDAO.consulta(txtBusca.getText(), select)));
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		txtBusca.setColumns(10);

		JLabel lblBusca = new JLabel("Busca:");

		JButton btnTable = new JButton("Atualizar JTable");
		btnTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apresentaDadosJTable();
			}
		});

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "id", "nome", "email", "idade" }));

		GroupLayout groupLayout = new GroupLayout(frmGerenciamentoDeContatos.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAtualizar, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblId)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNome)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEmail)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblIdade)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnGravar, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnTable, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnDeletar, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(lblBusca)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtBusca, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtBusca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblBusca)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblId))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNome)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmail))
									.addGap(12)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIdade))))
							.addGap(22)
							.addComponent(btnGravar)
							.addGap(15)
							.addComponent(btnAtualizar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDeletar)
							.addGap(11)
							.addComponent(btnTable)))
					.addContainerGap(9, Short.MAX_VALUE))
		);

		jTableMostraContatos = new JTable();
		jTableMostraContatos.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane1.setViewportView(jTableMostraContatos);

		jTableMostraContatos.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Id", "Nome", "Email", "Idade" }));
		jTableMostraContatos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// obtem o índice da linha selecionada
				int i = jTableMostraContatos.getSelectedRow();

				model = jTableMostraContatos.getModel();

				// Apresenta a linha selecionada nos campos.
				txtId.setText(model.getValueAt(i, 0).toString());

				txtNome.setText(model.getValueAt(i, 1).toString());

				txtEmail.setText(model.getValueAt(i, 2).toString());

				txtIdade.setText(model.getValueAt(i, 3).toString());

			}
		});
		frmGerenciamentoDeContatos.getContentPane().setLayout(groupLayout);
	}
}