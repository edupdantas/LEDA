package atividade04;

import java.lang.ref.PhantomReference;

public class BST implements BST_IF {
    public Integer raiz;
    public BST esq;
    public BST dir;
    public int cont = 0;
    BST (){}
    BST (Integer data){
        this.raiz = data;
    }
    public boolean isEmpty() {
        return this.raiz == null;
    }
    @Override
    public void insert(Integer element) {
        cont++;
        if(isEmpty()) {
            this.raiz = element;
        }else {
            if (element < raiz) {
                if (esq == null) {
                    esq = new BST(element);
                } else {
                    esq.insert(element);
                }
            } else if (element > raiz) {
                if (dir == null) {
                    dir = new BST(element);
                } else {
                    dir.insert(element);
                }
            } else {
                cont--;
            }
        }
    }

    @Override
    public Integer search(Integer element) throws Exception {
        if(isEmpty()) {
            return null;
        }else {
            if(raiz.equals(element)) {
                return element;
            }else if(element < raiz && esq != null) {
                return esq.search(element);
            }else if(element > raiz && dir != null) {
                return dir.search(element);
            }else {
                return null;
            }
        }
    }

    @Override
    public int[] preOrder() {
        ListaEncadeada preOrder = new ListaEncadeada();
        preAux(preOrder,this);
        Integer integerArr[] = preOrder.toArray();
        int intArr[] = new int[cont];
        for (int i=0;cont>i;i++){
            intArr[i] = integerArr[cont-1-i];
        }
        return intArr;
    }
    public void preAux(ListaEncadeada lista,BST arvore){
        if(arvore != null) {
            lista.insert(arvore.raiz);
            preAux(lista,arvore.esq);
            preAux(lista,arvore.dir);
        }
    }
    @Override
    public int[] order() {
        ListaEncadeada order = new ListaEncadeada();
        orderAux(order,this);
        Integer integerArr[] = order.toArray();
        int intArr[] = new int[cont];
        for (int i=0;cont>i;i++){
            intArr[i] = integerArr[cont-1-i];
        }
        return intArr;
    }
    public void orderAux(ListaEncadeada lista,BST arvore){
        if(arvore != null) {
            orderAux(lista,arvore.esq);
            lista.insert(arvore.raiz);
            orderAux(lista,arvore.dir);
        }
    }
    @Override
    public int[] postOrder() {
        ListaEncadeada posOrder = new ListaEncadeada();
        posAux(posOrder,this);
        Integer integerArr[] = posOrder.toArray();
        int intArr[] = new int[cont];
        for (int i=0;cont>i;i++){
            intArr[i] = integerArr[cont-1-i];
        }
        return intArr;
    }
    public void posAux(ListaEncadeada lista,BST arvore){
        if(arvore != null) {
            posAux(lista,arvore.esq);
            posAux(lista,arvore.dir);
            lista.insert(arvore.raiz);
        }
    }
    @Override
    public boolean isComplete() {
        int index = 0;
        int count = countNo(this);
        return isComplete(this, index, count);
    }
    private boolean isComplete(BST bst, int index, int count) {
        if (bst == null) {
            return true;
        }
        if (index >= count) {
            return false;
        }
        return isComplete(bst.esq, 2 * index + 1, count) && isComplete(bst.dir, 2 * index + 2, count);
    }
    private int countNo(BST bst) {
        if (bst == null) {
            return 0;
        }
        return 1 + countNo(bst.esq) + countNo(bst.dir);
    }
}
