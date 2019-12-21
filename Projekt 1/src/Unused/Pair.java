package Unused;

public class Pair<L, R> {
    private L left;
    private R right;

    public Pair(L left, R right){
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public void setRight(R right) {
        this.right = right;
    }

    public boolean empty(){
        return this.left == null && this.right == null;
    }

    @Override
    public String toString() {
        if(left!=null) return(left.toString());
        else if(right!=null) return(right.toString());
        return(" ");
    }
}
