public class Matrix {
    double[]data;
    int rows;
    int cols;
    //...
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    Matrix(double[][] d){
        this.rows = d.length;

        int index = 0;
        int maxColumns = 0;
        for(int i = 0; i < rows; i++)
        {
            if(maxColumns < d[i].length)
            {
                maxColumns = d[i].length;
            }
        }

        this.cols = maxColumns;
        data = new double[rows*cols];

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(d[i].length > j)
                {
                    data[index] = d[i][j];
                }
                else
                {
                    data[index] = 0;
                }
                index++;
            }
        }
    }

    double[][] asArray()
    {
        double[][] array = new double[rows][cols]; //przypisanie (utworzenie)
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                array[i][j] = data[cols*i+j];
            }
        }
        return array;
    }

    double get(int r,int c)
    {
        if(r < rows && c < cols)
            return data[r*cols+c];
        else
            return 0;
    }

    void set (int r,int c, double value)
    {
        if(r < rows && c < cols)
            data[r*cols+c] = value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j = 0; j < cols; j++)
            {
                buf.append(data[i*cols+j]);
                if(j+1 < cols)
                    buf.append(",");
                else
                {
                    buf.append("]");
                    if(i+1 < rows)
                        buf.append(" \n");
                }
            }
        }
        buf.append("]");
        //...
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

        rows = newRows;
        cols = newCols;
        int index = 0;

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                data[cols*i+j] = data[index];
                index++;
            }
        }

    }

    int[] shape() {
        int [] s = new int[2];
        s[0] = rows;
        s[1] = cols;
        return s;
    }

    Matrix add(Matrix m){

        if(m.cols != cols || m.rows != rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be added to %d x %d",rows,cols,m.rows,m.cols));

        Matrix a = new Matrix(m.rows, m.cols);

        for(int i = 0; i < rows*cols; i++)
        {
            a.data[i] = data[i] + m.data[i];
        }

        return a;
    }

    Matrix sub(Matrix m){

        if(m.cols != cols || m.rows != rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be subbed to %d x %d",rows,cols,m.rows,m.cols));

        Matrix a = new Matrix(m.rows, m.cols);

        for(int i = 0; i < rows*cols; i++)
        {
            a.data[i] = data[i] - m.data[i];
        }

        return a;
    }

    Matrix mul(Matrix m){

        if(m.cols != cols || m.rows != rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be multiplicated to %d x %d",rows,cols,m.rows,m.cols));

        Matrix a = new Matrix(m.rows, m.cols);

        for(int i = 0; i < rows*cols; i++)
        {
            a.data[i] = data[i] * m.data[i];
        }

        return a;
    }

    Matrix div(Matrix m){

        if(m.cols != cols || m.rows != rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be divided to %d x %d",rows,cols,m.rows,m.cols));

        Matrix a = new Matrix(m.rows, m.cols);

        for(int i = 0; i < rows*cols; i++)
        {
            a.data[i] = data[i] / m.data[i];
        }

        return a;
    }

    Matrix add(double w){

        Matrix a = new Matrix(rows, cols);

        for(int i = 0; i < rows*cols; i++)
        {
            a.data[i] = data[i] + w;
        }

        return a;
    }

    Matrix sub(double w){

        Matrix a = new Matrix(rows, cols);

        for(int i = 0; i < rows*cols; i++)
        {
            a.data[i] = data[i] - w;
        }

        return a;
    }

    Matrix mul(double w){

        Matrix a = new Matrix(rows, cols);

        for(int i = 0; i < rows*cols; i++)
        {
            a.data[i] = data[i] * w;
        }

        return a;
    }

    Matrix div(double w){

        Matrix a = new Matrix(rows, cols);

        for(int i = 0; i < rows*cols; i++)
        {
            a.data[i] = data[i] / w;
        }

        return a;
    }

    Matrix dot(Matrix m)
    {
        if(rows != m.cols || cols != m.rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be multiplied through %d x %d",rows,cols,m.rows,m.cols));

        Matrix a = new Matrix(rows, m.cols);

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                double value = 0;
                for(int k = 0; k < m.rows; k++)
                {
                    value += get(i,k)*m.get(k,j);
                }
                a.set(i,j, value);
            }
        }
        return  a;
    }

    public static void main(String[] args) {

        java.util.Scanner input = new java.util.Scanner(System.in);


        int c = 2;
        int d = 3;

        double[][] array = new double[c][d];
        double[][] array2 = new double[d][c];
        for(int i = 0; i < c ; i++)
        {
            for(int j = 0; j < d; j++)
            {
                array[i][j] = 3.0*(j+2);
                array2[j][i] = 2.0*(i+3);
            }
        }

        for(int i = 0; i < c ; i++)
        {
            for(int j = 0; j < d; j++)
            {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        for(int i = 0; i < d ; i++)
        {
            for(int j = 0; j < c; j++)
            {
                System.out.print(array2[i][j] + " ");
            }
            System.out.print("\n");
        }

        Matrix mat1 = new Matrix(array);
        Matrix mat2 = new Matrix(array2);

        System.out.print(mat1 + "\n");
        System.out.print(mat2 + "\n");

        System.out.print("Multiplication (m1 x m2) result: " + mat2.dot(mat1) + "\n");

        Matrix matrix = new Matrix(new double[][]{{1},{1,2,4}});
        double [][] x = matrix.asArray();
        int counter = 0;
        for(int i = 0; i < x.length; i++)
        {
            for(int j = 0; j < x[0].length; j++)
            {
                System.out.print(x[i][j] + " ");
                if(x[i][j] == 0)
                    counter++;
            }
            System.out.print("\n");
        }

        double val = 2.5;
        Matrix m=  new Matrix(3,3);
        m.set(2,2,val);
        System.out.print(m.toString() + "\n");
        m.reshape(1,9);
        System.out.print(m.toString() + "\n");

    }

}

