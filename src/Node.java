import javax.swing.text.StyledEditorKit.BoldAction;

public class Node implements Cloneable {

    // store node info
    public String[][] nodeInfo = new String[4][4];

    // default constructor
    public Node() {
    }

    // constructor
    public Node(String[][] nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    // override cloneable meethod
    @Override
    public Node clone() throws CloneNotSupportedException {
        // todo : clone new object from node here
        return null;
    }

    // override to string method
    @Override
    public String toString() {
        return nodeInfo.toString();
    }
}