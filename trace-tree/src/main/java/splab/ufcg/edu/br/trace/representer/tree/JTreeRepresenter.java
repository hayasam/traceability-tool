package splab.ufcg.edu.br.trace.representer.tree;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import splab.ufcg.edu.br.trace.entities.TraceLinkList;
import splab.ufcg.edu.br.trace.enumeration.TraceLinkElementEnum;
import splab.ufcg.edu.br.trace.interfaces.Representable;

public class JTreeRepresenter   implements Representable{
	
	private javax.swing.JPanel representationPanel;
	private javax.swing.JScrollPane treePanel;
	private javax.swing.JTree traceTree;
	
	private javax.swing.JButton expandCollapseButton;
	
	private boolean expand;

	private TraceLinkList lastResult;
	private javax.swing.JFrame parent;

	
	public JTreeRepresenter(javax.swing.JFrame parent){
		this.parent = parent;
		representationPanel = new javax.swing.JPanel();
		treePanel = new javax.swing.JScrollPane();
        traceTree = new javax.swing.JTree();
        expandCollapseButton = new javax.swing.JButton();
		
        expand = false;
		
		
		this.initComponents();
	}
	
	

	private void initComponents() {
		
		representationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		
		traceTree.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Retrieved Trace Links"));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Trace Links");
        traceTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        traceTree.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        treePanel.setViewportView(traceTree);
        
        expandCollapseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/expand.gif")));
        expandCollapseButton.setSize(new java.awt.Dimension(27, 27));
        expandCollapseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expandCollapseButtonActionPerformed(evt);
            }
        });
		
		
		javax.swing.GroupLayout representationPanelLayout = new javax.swing.GroupLayout(representationPanel);
        representationPanel.setLayout(representationPanelLayout);
        representationPanelLayout.setHorizontalGroup(
            representationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, representationPanelLayout.createSequentialGroup()
                .addGap(0, 413, Short.MAX_VALUE)
                .addComponent(expandCollapseButton))
            .addGroup(representationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treePanel)
                .addContainerGap())
        );
        representationPanelLayout.setVerticalGroup(
            representationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, representationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expandCollapseButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(parent.getContentPane());
        parent.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(representationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    )
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(representationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        )
                    )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)                
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

		
	}
	
	
	private void expandCollapseButtonActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_expandCollapseButtonActionPerformed
		DefaultTreeModel model = (DefaultTreeModel) traceTree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

		expand = !expand;

		if (expand) {
			expandCollapseButton.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/icons/collapse.gif")));

			for (int i = 0; i < traceTree.getRowCount(); i++) {
				traceTree.expandRow(i);
			}
		} else {
			expandCollapseButton.setIcon(new javax.swing.ImageIcon(getClass()
					.getResource("/icons/expand.gif")));

			for (int i = 0; i < traceTree.getRowCount(); i++) {
				traceTree.collapseRow(i);
			}
		}
		// model.reload(root);
	}



	@Override
	public JPanel getRepresentation(TraceLinkList tracelinks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void filter(TraceLinkElementEnum filterElement, String filterValue) {
		// TODO Auto-generated method stub
		
	}

}
