package clubseneca.comercial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UbicacionInventario implements Serializable {
    private String nombre;
    private List<StockArticulo> stocks;

    public UbicacionInventario(String nombre) {
        this.nombre = nombre;
        this.stocks = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<StockArticulo> getStocks() {
        return new ArrayList<>(stocks);
    }

    public StockArticulo buscarStock(ArticuloTienda articulo) {
        for (StockArticulo stock : stocks) {
            if (stock.getArticulo() == articulo) {
                return stock;
            }
        }
        return null;
    }

    public void agregarStock(StockArticulo stock) {
        if (stock != null && stock.getUbicacion() == this && !stocks.contains(stock)) {
            stocks.add(stock);
        }
    }
}
