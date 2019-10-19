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
        this.cols = d[0].length;
        int index = 0;
        int maxColumns = 0;
        for(int i = 0; i < rows; i++)
        {
            if(maxColumns < d[i].length)
            {
                maxColumns = d[i].length;
            }
        }

        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < maxColumns; j++)
            {
                if(d[i].length < j)
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
                buf.append(data[i*rows+j]);
                if(j+1 < cols)
                    buf.append(",");
                else
                    buf.append("] \n");
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

}


System.out.print("Enter matrix n x m: ");
        int n = input.nextInt();
        int m = input.nextInt();
        Matrix matrix = new Matrix(n,m);
        for(int i = 0; i < n*m; i++)
        {
            matrix.data[i] = input.nextDouble();
        }
        System.out.print(matrix);

System.out.print("Enter matrix n x m: ");
        int a = input.nextInt();
        int b = input.nextInt();
        Matrix matrix2 = new Matrix(a,b);
        for(int i = 0; i < a*b; i++)
        {
        matrix2.data[i] = input.nextDouble();
        }
        System.out.print(matrix2);



        System.out.print("Multiplication (m1 x m2) result: " + matrix.dot(matrix2));
