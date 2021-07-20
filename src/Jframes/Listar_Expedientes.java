/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jframes;
import Inicio.Denuncias;
import Inicio.Expediente;
import Otros.contr_Expedientes;
import Inicio.contr_Funcionario;
import Inicio.Funcionario;
import Inicio.vehiculo;
import Otros.Limpiar_txt;
import Otros.contr_denuncias;
import Otros.contr_vehiculo;
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
public class Listar_Expedientes extends javax.swing.JFrame {

    /**
     * Creates new form Visual
     */
    
    Limpiar_txt lt = new Limpiar_txt();
    
    private String ruta_txt = "Expe_conductores.txt"; 
   // private String Sruta_txt="Denuncias.txt";
    vehiculo v;
    contr_vehiculo cv;
    Denuncias d;
    contr_denuncias cd;
    int clic_tabla;
            
    
    
    public Listar_Expedientes() {
          initComponents();
        cv = new contr_vehiculo();
        cd = new contr_denuncias();

        try{
                        
            cargarexp();
           // cargar_txt();
            listarRegistro();
        }catch(Exception ex){
            mensaje("No existe el archivo txt");
        }
    }

    public void cargar_txt(){
        File ruta = new File("Denuncia_vehiculos.txt");
        try{
            
            FileReader fi = new FileReader(ruta);
            BufferedReader bu = new BufferedReader(fi);
            
            
            String linea = null;
            while((linea = bu.readLine())!=null){
                StringTokenizer st = new StringTokenizer(linea, ",");
                v = new vehiculo();
                v.setId_propietario(Integer.parseInt(st.nextToken()));
                v.setNombre_propietario(st.nextToken());
                v.setTelefono(st.nextToken());
                v.setDireccion_propietario(st.nextToken());
                v.setMarca(st.nextToken());
                v.setModelo(st.nextToken());
                v.setPlaca_vehiculo(st.nextToken());
                v.setEmail_prop(st.nextToken());
                cv.agregarRegistro(v);
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
            else  if(leeridpropietario() == -666)mensaje("Ingresar identificacion del infractor");
            else if(leerNombre() == null)mensaje("Ingresar Nombre");
            else if(leerplaca() == null) mensaje("Ingresar la placa del vehiculo");
            else if(leerDireccion() == null) mensaje("Ingresar direccion");
            else if(leeremail() == null) mensaje("Ingresar Email infractor");
            else if(leertelefono() == null)mensaje("Ingresar Telefono");
            else{
                d = new Denuncias (leerId_denuncia(),leeridpropietario(), leerNombre(), leerplaca(), leerDireccion(),leeremail(),leertelefono(),leerEstado());
                if(cd.buscaCodigo(d.getId_denuncia())!= -1){
                    mensaje("Este registro ya existe");
                    
                    lt.limpiar_texto(panel);
                   
                }
               else cd.agregarRegistro(d);{
               int s = JOptionPane.showConfirmDialog(null, "SE ABRIRA UN NUEVO EXPEDIENTE SANCIONADOR. "+"\n confirma que los datos estan correctos? ","Si/No",0);
                    if(s == 0){ 
                        grabar_txt();
                        eliminarRegistro();
                listarRegistro();
                lt.limpiar_texto(panel);
                    }
            }
            
            }
            
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
    public void modificarRegistro(File ruta){
        try{
            if(leeridpropietario() == -666)mensaje("Ingresar nueva identificacion de propietario");
            else if(leerNombre() == null)mensaje("Ingresar Nueva Nombre de propietario");
            else if(leertelefono() == null) mensaje("Ingresar Nuevo telefono");
            else if(leerDireccion() == null)mensaje("Ingresar Nueva Direccion de propietario");
           // else if(leermarca() == null) mensaje("Ingresar marca");
           // else if(leermodelo() == null) mensaje("Ingresar modelo");
            else if(leerplaca() == null) mensaje("Ingresar PLACA vehiculo");
           else if(leeremail() == null) mensaje("ingresar nuevo Email de propietario");
           
            else{
                int id_propietario = cv.buscaCodigo(leeridpropietario());
             //   v = new vehiculo (leeridpropietario(), leerNombre(), leertelefono(),leerDireccion(),leermarca() ,leermodelo(),leerplaca(),leeremail());
                if(id_propietario == -1)cv.agregarRegistro(v);
                else cv.modificarRegistro(id_propietario, v);
                
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
            if(leeridpropietario() == -666) mensaje("Ingrese identificacion entero");
            
            else{
                int ide_inf = cv.buscaCodigo(leeridpropietario());
                if(ide_inf == -1) mensaje("codigo no existe");
                
                else{
                    int s = JOptionPane.showConfirmDialog(null, "Esta seguro de Abrir este Expediente? \n -Se eliminara el registro de la denuncia y se creara un expediente sancionador","Si/No",0);
                    if(s == 0){
                        cv.eliminarRegistro(ide_inf);
                        
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
            fw = new FileWriter("Denuncia_vehiculos.txt");;
            pw = new PrintWriter(fw);
            //sp = new PrintWriter(Sruta_txt);
            for(int i = 0; i < cv.cantidadRegistro(); i++){
                v = cv.obtenerRegistro(i);
                pw.println(String.valueOf(v.getId_propietario()+", "+v.getNombre_propietario()+", "+v.getPlaca_vehiculo()+", "+v.getDireccion_propietario()+", "+v.getEmail_prop()+", "+v.getTelefono()));
              
                        
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
        dt.addColumn("Id Denuncia");
        dt.addColumn("Identificacion");
        dt.addColumn("NOMBRE");
        dt.addColumn("DIRECCION");
        dt.addColumn("PLACA");
        dt.addColumn("EMAIL");
        dt.addColumn("TELEFONO");
        dt.addColumn("ESTADO");
        
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < cd.cantidadRegistro(); i++){
            d = cd.obtenerRegistro(i);
            fila[0] = d.getId_denuncia();
            fila[1] = d.getId_infractor();
            fila[2] = d.getNombre_infractor();
            fila[3] = d.getDireccion();
            fila[4] = d.getPlaca_vehiculo();
            fila[5] = d.getEmail();
            fila[6] = d.getTelefono();
            fila[7] = d.getEstado();
            
            dt.addRow(fila);
        }
        tabla.setModel(dt);
        tabla.setRowHeight(60);
    }
    
     public int leerId_denuncia(){
    try{
            int ide_denun = Integer.parseInt(txtdenun_vehi.getText().trim());
            return ide_denun;
        }catch(Exception ex){
            return -666;
        }
    }
    
    
    public int leeridpropietario(){
        try{
            int id_propietario = Integer.parseInt(txtideprop.getText().trim());
            return id_propietario;
        }catch(Exception ex){
            return -666;
        }
    }
   
    public String leerNombre(){
        try{
            String nombre = txtnombreprop.getText().trim();
            return nombre;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String leertelefono(){
        try{
            String telefono =txttelefprop.getText() .trim();
            return telefono;
        }catch(Exception ex){
            return null;
        }
    }
    
      public String leerDireccion(){
        try{
            String direccion = txtRuta.getText().trim();
            return direccion;
        }catch(Exception ex){
            return null;
        }
    }
  
         public String leerestado(){
        try{
            String estado = txtESTADO.getText().trim();
            return estado;
        }catch(Exception ex){
            return null;
        }
    }
    
   
    public String leerplaca(){
        try{
            String placa  = txtplaca.getText().trim();
            return placa;
        }catch(Exception ex){
            return null;
        }
    }
     public String leeremail(){
        try{
            String email = txtemail.getText().trim();
            return email;
        }catch(Exception ex){
            return null;
        }
    }
 
    public String leerEstado(){
     String estado = " ";
     return estado;
    
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
            java.util.logging.Logger.getLogger(VisualAgenteTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualAgenteTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualAgenteTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualAgenteTra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Listar_Expedientes().setVisible(true);
            }
        });
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        txtRuta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtplaca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        txtESTADO = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtideprop = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnombreprop = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttelefprop = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtdenun_vehi = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Base de Datos con Bloc de Notas .txt");

        panel.setBackground(new java.awt.Color(153, 153, 255));

        txtRuta.setEditable(false);
        txtRuta.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Direccion de propietario:");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Estado:");

        txtplaca.setEditable(false);
        txtplaca.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("Placa:");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Jframes/limpiar.png")));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtESTADO.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Identificacion propietario:");

        txtideprop.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombre infractor:");

        txtnombreprop.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Telefono:");

        txttelefprop.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("email:");

        txtemail.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("ID Denuncia");

        txtdenun_vehi.setEditable(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(22, 22, 22)
                                    .addComponent(txtplaca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnombreprop, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(310, 310, 310))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel10))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addComponent(txttelefprop, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtideprop, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtdenun_vehi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton5))
                        .addGap(404, 404, 404)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtideprop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtdenun_vehi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtnombreprop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttelefprop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtESTADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtplaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText("salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel6.setText("Lista de Expedientes :");
        jLabel6.setToolTipText("");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title7", "Title 8"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel12.setText("informacion del registro:");
        jLabel12.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        
        clic_tabla = tabla.rowAtPoint(evt.getPoint());
        int id_denuncia= (int)tabla.getValueAt(clic_tabla, 0);
        int id_propietario = (int)tabla.getValueAt(clic_tabla, 1);
       String nombre = ""+tabla.getValueAt(clic_tabla, 2);
       String direccion = ""+tabla.getValueAt(clic_tabla, 3);
       String placa= ""+tabla.getValueAt(clic_tabla, 4);
       String email= ""+tabla.getValueAt(clic_tabla, 5);
       String telefono = ""+tabla.getValueAt(clic_tabla, 6);
       String estado = ""+tabla.getValueAt(clic_tabla, 7);
        
       // String modelo = ""+tabla.getValueAt(clic_tabla, 5);
        
        
          
        txtdenun_vehi.setText(String.valueOf(id_denuncia));
        txtideprop.setText(String.valueOf(id_propietario));
        txtnombreprop.setText(nombre);
        txtRuta.setText((direccion));
        txtplaca.setText(placa);
        txtemail.setText(email);
        txttelefprop.setText(telefono);
        txtESTADO.setText(estado );
       
        
//cargobox.setSelectedItem(String.valueOf(cargo));
        
        
        //try{
          //  JLabel lbl = (JLabel)tabla.getValueAt(clic_tabla, 4);
            //lblFoto.setIcon(lbl.getIcon());
        //}catch(Exception ex){
        //}
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Limpiar_txt lp = new Limpiar_txt();
        lp.limpiar_texto(panel);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        VisualAnalistaTra pp = new VisualAnalistaTra();
          pp.setVisible(true);
          dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaActionPerformed

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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtESTADO;
    private javax.swing.JTextField txtRuta;
    private javax.swing.JTextField txtdenun_vehi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtideprop;
    private javax.swing.JTextField txtnombreprop;
    private javax.swing.JTextField txtplaca;
    private javax.swing.JTextField txttelefprop;
    // End of variables declaration//GEN-END:variables
}
