using System;
using Gtk;
using MySql.Data.MySqlClient;

using Serpis.Ad;
            
public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();

        App.Instance.Connection = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas");
        App.Instance.Connection.Open();

        ComboBoxHelper.Fill(comboBox, "select id, nombre from categoria order by nombre", 2);

    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}
