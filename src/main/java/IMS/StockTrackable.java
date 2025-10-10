package IMS;

interface StockTrackable {
    void receive(int qty);
    boolean issue(int qty);
}