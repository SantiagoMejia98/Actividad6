package Friends;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private Container contenedor;
    private JLabel nombre, numero;
    private JTextField campoNombre, campoNumero;
    private JButton create, read, update, delete, clear;

    public VentanaPrincipal() {
        inicio();
        setTitle("Contactos");
        setSize(280,210);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void inicio(){
        contenedor = getContentPane();
        contenedor.setLayout(null);

        nombre = new JLabel();
        nombre.setText("Nombre: ");
        nombre.setBounds(20, 20, 135, 23);
        campoNombre = new JTextField();
        campoNombre.setBounds(100, 20, 150, 23);

        numero = new JLabel();
        numero.setText("Número: ");
        numero.setBounds(20, 50, 135, 23);
        campoNumero = new JTextField();
        campoNumero.setBounds(100, 50, 150, 23);

        create = new JButton();
        create.setText("Create");
        create.setBounds(10, 80, 75, 23);
        create.addActionListener(this);

        read = new JButton();
        read.setText("Read");
        read.setBounds(95, 80, 75, 23);
        read.addActionListener(this);

        update = new JButton();
        update.setText("Update");
        update.setBounds(180, 80, 75, 23);
        update.addActionListener(this);

        delete = new JButton();
        delete.setText("Delete");
        delete.setBounds(55, 113, 75, 23);
        delete.addActionListener(this);

        clear = new JButton();
        clear.setText("Clear");
        clear.setBounds(140, 113, 75, 23);
        clear.addActionListener(this);

        contenedor.add(nombre);
        contenedor.add(campoNombre);
        contenedor.add(numero);
        contenedor.add(campoNumero);
        contenedor.add(create);
        contenedor.add(read);
        contenedor.add(update);
        contenedor.add(delete);
        contenedor.add(clear);
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource() == create){
            create();
        }

        if (event.getSource() == read){
            read();
        }

        if (event.getSource() == update){
            update();
        }

        if (event.getSource() == delete){
            delete();
        }

        if (event.getSource() == clear){
            campoNombre.setText("");
            campoNumero.setText("");
        }
    }

    public void create(){
        boolean error = false;
            String nombre;
            Long numero;
            try {
                nombre = campoNombre.getText();
                numero = Long.parseLong(campoNumero.getText());

                if (nombre.isEmpty()) {
                    throw new Exception();
                }

                CRUD crud = new CRUD();
                String mensaje = crud.addFriend(nombre, numero);
                JOptionPane.showMessageDialog(contenedor, mensaje);
            } catch (Exception e){
                error = true;
            } finally {
                if (error) {
                    JOptionPane.showMessageDialog(null,"Debes ingresar un nombre o número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }

    public void read(){
        boolean error = false;
            String nombre;
            try {
                nombre = campoNombre.getText();
                if (nombre.isEmpty()) {
                    throw new Exception();
                }

                CRUD crud = new CRUD();
                Long numero = crud.readFriend(nombre);
                if (numero != null) {
                    campoNumero.setText(String.valueOf(numero));
                } else {
                    JOptionPane.showMessageDialog(contenedor, "El contacto no existe");
                }
            } catch (Exception e){
                error = true;
            } finally {
                if (error) {
                    JOptionPane.showMessageDialog(null,"Debes ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }

    public void update(){
        boolean error = false;
            String nombre;
            Long numero;
            try {
                nombre = campoNombre.getText();
                numero = Long.parseLong(campoNumero.getText());

                if (nombre.isEmpty()) {
                    throw new Exception();
                }

                CRUD crud = new CRUD();
                String mensaje = crud.updateFriend(nombre, numero);
                JOptionPane.showMessageDialog(contenedor, mensaje);
            } catch (Exception e){
                error = true;
            } finally {
                if (error) {
                    JOptionPane.showMessageDialog(null,"Debes ingresar un nombre o número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }

    public void delete(){
        boolean error = false;
            String nombre;
            try {
                nombre = campoNombre.getText();

                if (nombre.isEmpty()) {
                    throw new Exception();
                }

                CRUD crud = new CRUD();
                String mensaje = crud.deleteFriend(nombre);
                JOptionPane.showMessageDialog(contenedor, mensaje);
            } catch (Exception e){
                error = true;
            } finally {
                if (error) {
                    JOptionPane.showMessageDialog(null,"Debes ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }
}
