package findrootofafunction;

public class LinkNode {

    private Double x;
    private Double fx;
    private LinkNode next;

    public LinkNode() {
        this(0.0, 0.0, null);
    }

    public LinkNode(Double i, Double j, LinkNode n) {
        x = i;
        fx = j;
        next = n;
    }

    // Get methods
    public Double getX() {
        return x;
    }
    public Double getFx() {
        return fx;
    }
    public LinkNode getNext() {
        return next;
    }

    // Set methods
    public void setX(Double newX) {
        x = newX;
    }
    public void setFx(Double newFx) {
        fx = newFx;
    }
    public void setNext(LinkNode newNext) {
        next = newNext;
    }
}
