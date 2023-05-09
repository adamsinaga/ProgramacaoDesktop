package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicial {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSistemas = new JMenu("Sistemas");
		menuBar.add(mnSistemas);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mntmVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chamarei outro formulário para apresentar tela de vendas
				TelaVendas telaVendas = new TelaVendas();				
				telaVendas.visualizarTela();
			}
		});
		mnSistemas.add(mntmVendas);
		
		JMenuItem mntmCompras = new JMenuItem("Compras");
		mnSistemas.add(mntmCompras);
		
		JMenu mnRelatrios = new JMenu("Relatórios");
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmVendas_1 = new JMenuItem("Vendas");
		mnRelatrios.add(mntmVendas_1);
		
		JMenuItem mntmCompras_1 = new JMenuItem("Compras");
		mnRelatrios.add(mntmCompras_1);
	}

}
