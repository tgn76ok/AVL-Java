public class Arvore {
    Elemento elemento;
    Arvore esquerda;
    Arvore direita;
    int balanicar;

    public Arvore() {
        this.elemento = null;
        this.esquerda = null;
        this.direita = null;
        this.balanicar = 0;

    }

    public Arvore(Elemento elem) {
        this.elemento = elem;
        this.direita = null;
        this.esquerda = null;
        this.balanicar = 0;

    }

    public Arvore busca_elemento(int valor) {
        Arvore no;

        if (this.elemento.getRGM() == valor) {
            no = this;
            return no;
        } else {
            if (valor < this.elemento.getRGM()) {
                if (this.esquerda == null) {
                    return null;
                } else {
                    this.esquerda.busca(valor);// repassei a responsabilidade para a subarvore esquerda
                }
            } else if (valor > this.elemento.getRGM()) {
                if (this.direita == null) {
                    return null;
                } else {
                    this.direita.busca(valor);
                }
            }

            return null;
        }
    }

    public boolean busca(int valor) {
        if (isEmpty()) {
            return false;
        }
        if (this.elemento.getRGM() == valor) {
            return true;
        } else {
            if (valor < this.elemento.getRGM()) {
                if (this.esquerda == null) {
                    return false;
                } else {
                    return this.esquerda.busca(valor); // repassei a responsabilidade para a subarvore esquerda
                }
            } else if (valor > this.elemento.getRGM()) {
                if (this.direita == null) {
                    return false;
                } else {
                    return this.direita.busca(valor);
                }
            }
            return false;
        }
    }

    public boolean isEmpty() {
        return (this.elemento == null);
    }
    // funçoes

    public Arvore remover(Elemento elemento) {
        // primeiro caso - achei o elemento
        if (this.elemento.getRGM() == elemento.getRGM()) {
            // caso mais simples - o elemento est� em um n� folha
            if (this.direita == null && this.esquerda == null) {
                return null;
            } else {
                // caso 2 - eu tenho filhos � esquerda, por�m n�o tenho � direita
                if (this.esquerda != null && this.direita == null) {
                    return this.esquerda;
                }
                // caso 3 - eu tenho filhos � direita, por�m n�o tenho � esquerda
                else if (this.direita != null && this.esquerda == null) {
                    return this.direita;
                }
                // caso 4 - tenho filhos dos dois lados (esquerda e direita)
                else {
                    // vamos adotar a estrat�gia do maior dentre os menores?
                    Arvore aux = this.esquerda;
                    while (aux.direita != null) { // enquanto houver filhos � direita
                        aux = aux.direita;
                    }
                    // troco os elementos da �rvore
                    this.elemento = aux.getElemento(); // o n� atual recebe o elemento do aux
                    // o maior dentre os menores
                    aux.setElemento(elemento); // insiro no n� folha (l� embaix�o) o elmento a ser eliminado
                    this.esquerda = esquerda.remover(elemento);
                }
            }
        } else if (elemento.getRGM() < this.elemento.getRGM()) {
            // delegar a responsabilidade � sub-arvore da esquerda
            this.esquerda = this.esquerda.remover(elemento);
        } else if (elemento.getRGM() > this.elemento.getRGM()) {
            // delegar a responsabilidade � sub-arvore da direita
            this.direita = this.direita.remover(elemento);
        }
        return this;
    }

    public Arvore inserir(Elemento neweElemento) {

        if (isEmpty()) {
            this.elemento = neweElemento;
        } else {
            Arvore novaArvore = new Arvore(neweElemento);
            if (neweElemento.getRGM() < this.elemento.getRGM()) {
                if (this.esquerda == null) {
                    this.esquerda = novaArvore;

                } else {
                    this.esquerda = this.esquerda.inserir(neweElemento); //
                }

            } else if (neweElemento.getRGM() > this.elemento.getRGM()) {

                if (this.direita == null) {
                    this.direita = novaArvore;
                } else {
                    this.direita = this.direita.inserir(neweElemento);
                }
            }
        }
        return this;

    }

    public int calcularAltura() {
        if (this.direita == null && this.esquerda == null) {
            return 1;
        } else if (this.esquerda != null && this.direita == null) {
            return 1 + this.esquerda.calcularAltura();
        } else if (this.direita != null && this.esquerda == null) {
            return 1 + this.direita.calcularAltura();

        } else {
            return 1 + Math.max(this.direita.calcularAltura(), this.esquerda.calcularAltura());
        }

    }
    // Aplicando AVL

    public void calcularBalanceamento() {
        if (this.direita == null && this.esquerda == null) {
            this.balanicar = 0;
        } else if (this.esquerda == null && this.direita != null) {
            this.balanicar = this.direita.calcularAltura() - 0;
        } else if (this.esquerda != null && this.direita == null) {
            this.balanicar = 0 - this.esquerda.calcularAltura();

        } else {
            this.balanicar = this.direita.calcularAltura() - this.esquerda.calcularAltura();

        }
        if (this.direita != null)
            this.direita.calcularBalanceamento();
        if (this.esquerda != null)
            this.esquerda.calcularBalanceamento();
    }

    public Arvore verificaBalanceamento() {
        if (this.balanicar >= 2 || this.balanicar <= -2) {
            if (this.balanicar >= 2) {
                if (this.balanicar * this.direita.getBalanicar() > 0) {
                    return rotacaoSimplesDireita();
                } else {
                    return rotacaoDuplaDireita();

                }
            } else {
                if (this.balanicar * this.esquerda.getBalanicar() < 0) {
                    return rotacaoSimplesEsquerda();
                } else {
                    return rotacaoDuplaEsquerda();
                }
            }
        }
        this.calcularBalanceamento();
        if (this.direita != null)
            this.direita = this.direita.verificaBalanceamento();
        if (this.esquerda != null)
            this.esquerda = this.esquerda.verificaBalanceamento();

        return this;
    }

    public Arvore rotacaoSimplesDireita() {
        Arvore filhoDireita;
        Arvore filhoDoFilho = null;

        filhoDireita = this.getDireita();
        if (this.direita != null) {
            if (this.direita.getEsquerda() != null) {
                filhoDoFilho = filhoDireita.getEsquerda();
            }
        }
        filhoDireita.setEsquerda(this);
        this.setDireita(filhoDoFilho);

        return filhoDireita;
    }

    public Arvore rotacaoSimplesEsquerda() {
        Arvore filhoEsquerda;
        Arvore filhoDoFilho = null;

        filhoEsquerda = this.getEsquerda();
        if (this.esquerda != null) {
            if (this.esquerda.getDireita() != null) {
                filhoDoFilho = filhoEsquerda.getDireita();
            }
        }
        filhoEsquerda.setDireita(this);
        this.setEsquerda(filhoDoFilho);

        return filhoEsquerda;
    }

    public Arvore rotacaoDuplaDireita() {
        Arvore arvore = this;
        Arvore filhoDireita = this.getDireita();
        Arvore filhoDoFilho = filhoDireita.getEsquerda();
        Arvore noInserido = filhoDoFilho.getDireita();

        filhoDireita.setEsquerda(noInserido);
        filhoDoFilho.setDireita(filhoDireita);
        this.setDireita(filhoDoFilho);

        Arvore newfilhoDireita = this.getDireita();
        arvore.setDireita(null);
        newfilhoDireita.setEsquerda(arvore);

        return newfilhoDireita;

    }

    public Arvore rotacaoDuplaEsquerda() {
        Arvore arvore = this;
        Arvore filhoEsquerda = this.getEsquerda();
        Arvore filhoDoFilho = filhoEsquerda.getDireita();
        Arvore noInserido = filhoDoFilho.getEsquerda();

        filhoEsquerda.setDireita(noInserido);
        filhoDoFilho.setEsquerda(filhoEsquerda);
        this.setEsquerda(filhoDoFilho);

        Arvore newfilhoEsquerda = this.getEsquerda();
        arvore.setEsquerda(null);
        newfilhoEsquerda.setDireita(arvore);

        return newfilhoEsquerda;
    }
    // funçoes de mostra a arvore

    public void imprimirPreOrdem() {
        if (!isEmpty()) {
            System.out.print(this.elemento.getRGM() + "  ");
            if (this.esquerda != null) {
                this.esquerda.imprimirPreOrdem();
            }
            if (this.direita != null) {
                this.direita.imprimirPreOrdem();
            }
        }
    }

    public void imprimirInOrdem() {
        if (!isEmpty()) {

            if (this.esquerda != null) {
                this.esquerda.imprimirPreOrdem();
            }
            System.out.print(this.elemento.getRGM() + "  ");
            if (this.direita != null) {
                this.direita.imprimirPreOrdem();
            }
        }
    }

    public void imprimirPosOrdem() {
        if (!isEmpty()) {

            if (this.esquerda != null) {
                this.esquerda.imprimirPreOrdem();
            }
            if (this.direita != null) {
                this.direita.imprimirPreOrdem();
            }
            System.out.print(this.elemento.getRGM() + "  ");
        }
    }

    public void esvaziarEmPosOrdem() {
        if (!isEmpty()) {

            if (this.esquerda != null) {
                this.esquerda.imprimirPreOrdem();
            }
            if (this.direita != null) {
                this.direita.imprimirPreOrdem();
            }
            this.esquerda = null;
            this.direita = null;

        }
    }

    public String printArvore(int level) {
        String str = this.toString() + "\n";
        for (int i = 0; i < level; i++) {
            str += "\t";
        }
        if (this.esquerda != null) {
            str += "+-ESQ: " + this.esquerda.printArvore(level + 1);
        } else {
            str += "+-ESQ: NULL";
        }
        str += "\n";

        for (int i = 0; i < level; i++) {
            str += "\t";
        }
        if (this.direita != null) {
            str += "+-DIR: " + this.direita.printArvore(level + 1);
        } else {
            str += "+-DIR: NULL";
        }
        str += "\n";
        return str;
    }

    public String toString() {
        return "[" + this.elemento.getRGM() + "] (" + this.balanicar + ")";
    }
    // get e set

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public Arvore getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Arvore esquerda) {
        this.esquerda = esquerda;
    }

    public Arvore getDireita() {
        return direita;
    }

    public void setDireita(Arvore direita) {
        this.direita = direita;
    }

    public int getBalanicar() {
        return balanicar;
    }

    public void setBalanicar(int balanicar) {
        this.balanicar = balanicar;
    }

}

/* falta buscar e balanicar essa arvore */
