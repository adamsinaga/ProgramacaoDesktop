package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.dao.VendasDAO;
import model.vo.Cliente;
import model.vo.Venda;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaVendas {

	private JFrame frmSistemasDeSvenda;
	private JTextField txtIdcliente;
	private JTextField txtNomecliente;
	private JTextField txtHistvenda;
	private JTextField txtEmissaoVnd;
	private JTextField txtNmrvenda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVendas window = new TelaVendas();
					window.frmSistemasDeSvenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaVendas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemasDeSvenda = new JFrame();
		frmSistemasDeSvenda.setFont(new Font("Dialog", Font.PLAIN, 12));
		frmSistemasDeSvenda.setTitle("Sistemas de Venda");
		frmSistemasDeSvenda.setBounds(100, 100, 480, 300);
		frmSistemasDeSvenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		txtIdcliente = new JTextField();
		txtIdcliente.setText("idCliente");
		txtIdcliente.setColumns(10);
		
		txtNomecliente = new JTextField();
		txtNomecliente.setText("nomeCliente");
		txtNomecliente.setColumns(28);
		
		txtHistvenda = new JTextField();
		txtHistvenda.setText("histVenda");
		txtHistvenda.setColumns(28);
		
		JLabel lblCdigoCliente = new JLabel("Código Cliente");
		
		JLabel lblNomeDoCliente = new JLabel("Nome do Cliente");
		
		JLabel lblHistrico = new JLabel("Histórico");
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Venda vnd = new Venda();				
				VendasDAO vndDao = new VendasDAO();
				
				//utilizando classe VO para transição/manutenção/processamento da informação
				vnd.setCliente(new Cliente(Integer.parseInt(txtIdcliente.getText()),txtNomecliente.getText()));
				vnd.setNmrVenda(Integer.parseInt(txtNmrvenda.getText()));
				vnd.setEmissao(txtEmissaoVnd.getText());
				vnd.setHistorico(txtHistvenda.getText());
				//enviando dados para DB
				
				vndDao.inserir(vnd);
				
				
				
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		
		txtEmissaoVnd = new JTextField();
		txtEmissaoVnd.setText("Emissão");
		txtEmissaoVnd.setColumns(10);
		
		JLabel lblEmisso = new JLabel("Emissão");
		
		txtNmrvenda = new JTextField();
		txtNmrvenda.setText("nmrVenda");
		txtNmrvenda.setColumns(10);
		
		JLabel lblNVenda = new JLabel("Nº Venda");
		GroupLayout groupLayout = new GroupLayout(frmSistemasDeSvenda.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCdigoCliente)
						.addComponent(lblNomeDoCliente)
						.addComponent(lblHistrico)
						.addComponent(lblNVenda))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNmrvenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNomecliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtIdcliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEmisso)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtEmissaoVnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnCancelar)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnGravar))
							.addComponent(txtHistvenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNmrvenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNVenda))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtIdcliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCdigoCliente))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblEmisso)
							.addComponent(txtEmissaoVnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtNomecliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtHistvenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNomeDoCliente)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblHistrico)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGravar)
						.addComponent(btnCancelar))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		frmSistemasDeSvenda.getContentPane().setLayout(groupLayout);
		
	}
	public void visualizarTela() {
		frmSistemasDeSvenda.setVisible(true);
	}
}
