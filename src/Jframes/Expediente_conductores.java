/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jframes;
import Inicio.Denuncias;
import Otros.imgTabla;
import Otros.contr_Infractor;
import Inicio.Infractor;
import Inicio.contr_Funcionario;
import Inicio.Funcionario;
import Otros.Limpiar_txt;
import Otros.contr_denuncias;
import Otros.imgTabla;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class Expediente_conductores extends javax.swing.JFrame {

    /**
     * Creates new form Visual
     */
    
    Limpiar_txt lt = new Limpiar_txt();
    //private String ruta_txt = "Denuncia_conductores.txt";
    private String ruta_txt = "Expe_conductores.txt"; 
    Denuncias d;
    Infractor in;
    contr_Infractor ci;
    contr_denuncias cd;
    int clic_tabla;
            
    public Expediente_conductores() {
        initComponents();
        ci = new contr_Infractor();
        cd = new contr_denuncias();
        try{
            
            cargarexp();
            cargar_txt();
            listarRegistro();
        }catch(Exception ex){
            mensaje("No existe el archivo txt");
        }
    }

    public void cargar_txt(){
         
        try{
            
            FileReader fi = new FileReader("Denuncia_conductores.txt");
            BufferedReader bu = new BufferedReader(fi);
            
            
            String linea = null;
            while((linea = bu.readLine())!=null){
                StringTokenizer st = new StringTokenizer(linea, ",");
                in = new Infractor();
                in.setIde_infractor(Integer.parseInt(st.nextToken()));
                in.setNombre(st.nextToken());
                in.setPlaca_vehiculo(st.nextToken());
                in.setDireccion_residencia(st.nextToken());               
                in.setEmail(st.nextToken());   
                in.setTelefono(st.nextToken());
                
                ci.agregarRegistro(in);
            }
            bu.close();
        }catch(Exception ex){
            mensaje("Error al cargar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    public void cargarexp(){
       File ruta = new File(ruta_txt);
        try{
            
            FileReader fi = new FileReader(ruta);
            BufferedReader bu = new BufferedReader(fi);
            
            
            String linea = null;
            while((linea = bu.readLine())!=null){
                StringTokenizer st = new StringTokenizer(linea, ",");
                d = new Denuncias();
                d.setId_denuncia(Integer.parseInt(st.nextToken()));
                d.setId_infractor(Integer.parseInt(st.nextToken()));
                d.setNombre_infractor(st.nextToken());
                d.setPlaca_vehiculo(st.nextToken());
                d.setDireccion(st.nextToken());               
                d.setEmail(st.nextToken());   
                d.setTelefono(st.nextToken());
                d.setEstado(st.nextToken());
                cd.agregarRegistro(d);
            }
            bu.close();
        }catch(Exception ex){
            mensaje("Error al cargar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
    
    public void grabar_txt(){
        FileWriter fw;
        PrintWriter pw;
        try{
            fw = new FileWriter(ruta_txt);
            pw = new PrintWriter(fw);
           
            for(int i = 0; i < cd.cantidadRegistro(); i++){
                d = cd.obtenerRegistro(i);
                pw.println(String.valueOf(d.getId_denuncia()+","+d.getId_infractor()+", "+d.getNombre_infractor()+", "+d.getPlaca_vehiculo()+", "+d.getDireccion()+", "+d.getEmail()+", "+d.getTelefono()+", "+"ACTIVO"));
               // pw.println(String.valueOf(d.getId_denuncia()+","+in.getIde_infractor()+", "+in.getNombre()+", "+in.getPlaca_vehiculo()+", "+in.getDireccion_residencia()+", "+in.getEmail()+", "+in.getTelefono()+", "+"ACTIVO"));
              
            }
             pw.close();
            // sp.close();            
        }catch(Exception ex){
            mensaje("Error al grabar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
    public void ingresarRegistro( File ruta){
        try{
          if(leerId_denuncia() == -666)mensaje("PORFAVOR INGRESE NUMERO DE DENUNCIA");
            else  if(leerIde_Infractor() == -666)mensaje("Ingresar identificacion del infractor");
            else if(leerNombre() == null)mensaje("Ingresar Nombre");
            else if(leerPlaca_vehiculo() == null) mensaje("Ingresar la placa del vehiculo");
            else if(leerDireccion() == null) mensaje("Ingresar direccion");
            else if(leerEmail() == null) mensaje("Ingresar Email infractor");
            else if(leerTelefono() == null)mensaje("Ingresar Telefono");
            else{
                d = new Denuncias (leerId_denuncia(),leerIde_Infractor(), leerNombre(), leerPlaca_vehiculo(), leerDireccion(),leerEmail(),leerTelefono(),leerEstado());
                if(cd.buscaCodigo(d.getId_denuncia())!= -1){
                    mensaje("Este registro ya existe");
                    
                    lt.limpiar_texto(panel);
                   
                }
               else cd.agregarRegistro(d);{
                
               int s = JOptionPane.showConfirmDialog(null, "SE ABRIRA UN NUEVO EXPEDIENTE SANCIONADOR. "+"\n confirma que los datos estan correctos? ","Si/No",0);
                    if(s == 0){ grabar_txt();
                eliminarRegistro();
                lt.limpiar_texto(panel);
                    
                    listarRegistro();
                    }
            }
            
            }
            
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
    public void modificarRegistro(File ruta){
        try{
            if(leerIde_Infractor() == -666)mensaje("Ingresar identificacion del infractor");
            else if(leerNombre() == null)mensaje("Ingresar Nuevo Nombre");
            else if(leerPlaca_vehiculo() == null) mensaje("Ingresar la placa del vehiculo");
            else if(leerDireccion() == null) mensaje("Ingresar nueva direccion");
            else if(leerEmail() == null) mensaje("Ingresar nuevo Email infractor");
            else if(leerTelefono() == null)mensaje("Ingresar nuevo Telefono");
            else{
                int ide_inf = ci.buscaCodigo(leerIde_Infractor());
                in = new Infractor (leerIde_Infractor(), leerNombre(), leerPlaca_vehiculo(), leerDireccion(),leerEmail(),leerTelefono());
                if(ide_inf == -1)ci.agregarRegistro(in);
                else ci.modificarRegistro(ide_inf, in);
                
                grabar_txt();
                listarRegistro();
                lt.limpiar_texto(panel);
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
   public void eliminarRegistro(){
        try{
            if(leerIde_Infractor() == -666) mensaje("Ingrese identificacion entero");
            
            else{
                int ide_inf = ci.buscaCodigo(leerIde_Infractor());
                if(ide_inf == -1) mensaje("codigo no existe");
                
                else{
                   int s = JOptionPane.showConfirmDialog(null, "Esta seguro de Abrir este Expediente? \n -Se eliminara el registro de la denuncia y se creara un expediente sancionador. \nSe notificara al infractor el cual poseera 30 dias para presentar apelacion o sera declarada inmediatamente la multa. ","Si/No",0);
                     if(s == 0){
                        ci.eliminarRegistro(ide_inf);
                        
                        grabar_txt2();
                        listarRegistro();
                        lt.limpiar_texto(panel);
                    }
                }
                
                
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
   
    public void grabar_txt2(){
        FileWriter fw;
        PrintWriter pw;
        try{
            fw = new FileWriter("Denuncia_conductores.txt");;
            pw = new PrintWriter(fw);
            //sp = new PrintWriter(Sruta_txt);
            for(int i = 0; i < ci.cantidadRegistro(); i++){
                in = ci.obtenerRegistro(i);
                pw.println(String.valueOf(in.getIde_infractor()+", "+in.getNombre()+", "+in.getPlaca_vehiculo()+", "+in.getDireccion_residencia()+", "+in.getEmail()+", "+in.getTelefono()));
              
                        
            }
             pw.close();
            // sp.close();            
        }catch(Exception ex){
            mensaje("Error al grabar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
   
   
   
   
    public void listarRegistro(){
        DefaultTableModel dt = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        dt.addColumn("Identificacion");
        dt.addColumn("Nombre");
        dt.addColumn("Placa vehiculo");
        dt.addColumn("Domicilio");
        dt.addColumn("Email");
        dt.addColumn("Telefono");
        
        tabla_inf.setDefaultRenderer(Object.class, new imgTabla());
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < ci.cantidadRegistro(); i++){
            in = ci.obtenerRegistro(i);
            fila[0] = in.getIde_infractor();
            fila[1] = in.getNombre();
            fila[2] = in.getPlaca_vehiculo(); 
            fila[3] = in.getDireccion_residencia();
            fila[4] = in.getEmail();
            fila[5] = in.getTelefono();
            dt.addRow(fila);
        }
        tabla_inf.setModel(dt);
        tabla_inf.setRowHeight(90);
    }
    public int leerId_denuncia(){
    try{
            int ide_denun = Integer.parseInt(txtide_denun.getText().trim());
            return ide_denun;
        }catch(Exception ex){
            return -666;
        }
    }
    public int leerIde_Infractor(){
        try{
            int ide_infractor = Integer.parseInt(txtide_infra.getText().trim());
            return ide_infractor;
        }catch(Exception ex){
            return -666;
        }
    }
    
    public String leerNombre(){
        try{
            String nombre = txtNombreinfra.getText().trim();
            return nombre;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leerPlaca_vehiculo(){
        try{
            String placa_vehiculo = txtplaca_inf.getText() .trim();
            return placa_vehiculo;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leerDireccion(){
        try{
            String direccion_residencia = txtRuta_inf.getText().trim();
            return direccion_residencia;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leerEmail(){
        try{
            String email = txtemailinf.getText().trim();
            return email;
        }catch(Exception ex){
            return null;
        }
    }
    
    
    public String leerTelefono(){
        try{
            String telefono = txtteleinf.getText().trim();
            return telefono;
        }catch(Exception ex){
            return null;
        }
    }

    
    
    public String leerEstado(){
     String estado = " ";
     return estado;
    
    }
    /*
    public byte[] leerFoto2(int codigo){
            p = rp.obtenerRegistro(codigo);
            try{
               return p.getFoto();
            }catch(Exception ex){
               return null;
            }
        }*/

    public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualAnalistaTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualAnalistaTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualAnalistaTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualAnalistaTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Expediente_conductores().setVisible(true);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtRuta_inf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreinfra = new javax.swing.JTextField();
        txtide_infra = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtplaca_inf = new javax.swing.JTextField();
        txtteleinf = new javax.swing.JTextField();
        txtemailinf = new javax.swing.JTextField();
        txtide_denun = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_inf = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Base de Datos con Bloc de Notas .txt");

        panel.setBackground(new java.awt.Color(204, 204, 255));

        jButton1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton1.setText("Abrir Expediente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtRuta_inf.setEditable(false);
        txtRuta_inf.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtRuta_inf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRuta_infActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Domicilio:");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Email");

        txtNombreinfra.setEditable(false);
        txtNombreinfra.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNombreinfra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreinfraActionPerformed(evt);
            }
        });

        txtide_infra.setEditable(false);
        txtide_infra.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtide_infra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtide_infraActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("identificacion");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("Placa vehiculo");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Jframes/limpiar.png")));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setText("Telefono");

        txtplaca_inf.setEditable(false);
        txtplaca_inf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtplaca_infActionPerformed(evt);
            }
        });

        txtteleinf.setEditable(false);

        txtemailinf.setEditable(false);

        txtide_denun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtide_denunActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("id Denuncia");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(6, 6, 6)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombreinfra, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(txtide_infra, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(60, 60, 60)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel7))
                                        .addGap(36, 36, 36)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtplaca_inf, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                            .addComponent(txtteleinf)))
                                    .addComponent(txtemailinf, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(391, 391, 391)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtide_denun, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 95, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(284, 284, 284)
                                .addComponent(jButton5))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtRuta_inf)))))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtide_denun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtide_infra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtplaca_inf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreinfra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtteleinf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtemailinf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtRuta_inf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41))))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel6.setText("Apertura de Expediente:");
        jLabel6.setToolTipText("");

        jButton4.setText("salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tabla_inf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla_inf.setCellSelectionEnabled(true);
        tabla_inf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_infMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_inf);

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel8.setText("Denuncias a Conductores");
        jLabel8.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 369, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(67, 67, 67))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      File ruta = new File(txtRuta_inf.getText());
      this.ingresarRegistro(ruta);
           
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabla_infMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_infMouseClicked
        
        clic_tabla = tabla_inf.rowAtPoint(evt.getPoint());
        
        int ide_infra = (int)tabla_inf.getValueAt(clic_tabla, 0);
        String nombre = ""+tabla_inf.getValueAt(clic_tabla, 1);
        String placa_vehi = ""+tabla_inf.getValueAt(clic_tabla, 2);
        String direccion = ""+tabla_inf.getValueAt(clic_tabla, 3);
        String email = ""+tabla_inf.getValueAt(clic_tabla, 4);
        String telefono = ""+tabla_inf.getValueAt(clic_tabla, 5);
        //Object descripcion = ""+tabla.getValueAt(clic_tabla, 3);

        
        
        txtide_infra.setText(String.valueOf(ide_infra));
        txtNombreinfra.setText(nombre);
        txtplaca_inf.setText(placa_vehi);
        txtRuta_inf.setText(direccion);
        txtemailinf.setText(email);
        txtteleinf.setText(telefono);
        
        //cargobox.setSelectedItem(String.valueOf(cargo));
        //txtRuta.setText(String.valueOf(descripcion));
        
        //try{
          //  JLabel lbl = (JLabel)tabla.getValueAt(clic_tabla, 4);
            //lblFoto.setIcon(lbl.getIcon());
        //}catch(Exception ex){
        //}
    }//GEN-LAST:event_tabla_infMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Limpiar_txt lp = new Limpiar_txt();
        lp.limpiar_texto(panel);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtplaca_infActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtplaca_infActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtplaca_infActionPerformed

    private void txtide_infraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtide_infraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtide_infraActionPerformed

    private void txtNombreinfraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreinfraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreinfraActionPerformed

    private void txtRuta_infActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRuta_infActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRuta_infActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         VisualAnalistaTra pp = new VisualAnalistaTra();
          pp.setVisible(true);
          dispose();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtide_denunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtide_denunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtide_denunActionPerformed

    /**
     * @param args the command line arguments
     */
   // public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //try {
        //    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
         //       if ("Nimbus".equals(info.getName())) {
          //          javax.swing.UIManager.setLookAndFeel(info.getClassName());
           //         break;
           //     }
         //   }
       // } catch (ClassNotFoundException ex) {
       //     java.util.logging.Logger.getLogger(VisualGestorFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
       // } catch (InstantiationException ex) {
        //    java.util.logging.Logger.getLogger(VisualGestorFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        //} catch (IllegalAccessException ex) {
         //   java.util.logging.Logger.getLogger(VisualGestorFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        //} catch (javax.swing.UnsupportedLookAndFeelException ex) {
         //   java.util.logging.Logger.getLogger(VisualGestorFuncionarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        //}
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
     //   java.awt.EventQueue.invokeLater(new Runnable() {
      //      public void run() {
      //          new VisualGestorFuncionarios().setVisible(true);
      //      }
      //  });
    //}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tabla_inf;
    private javax.swing.JTextField txtNombreinfra;
    private javax.swing.JTextField txtRuta_inf;
    private javax.swing.JTextField txtemailinf;
    private javax.swing.JTextField txtide_denun;
    private javax.swing.JTextField txtide_infra;
    private javax.swing.JTextField txtplaca_inf;
    private javax.swing.JTextField txtteleinf;
    // End of variables declaration//GEN-END:variables
}
