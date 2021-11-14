import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;

import static java.lang.Math.max;

public class Polinom {
    protected ArrayList<Double> coef_arr = new ArrayList<>();

    public Polinom() {
        this.coef_arr.add(0d);
    }

    public Polinom(ArrayList<Double> arr) {
        this.coef_arr = new ArrayList<>(arr);
    }

    private static int getRandomDiceNumber()
    {
        return (int) (Math.random() * 10) - 5;
    }

    public Polinom(int count){
        for (int i = 0; i < count; i++)
        {
            double x = getRandomDiceNumber();
            //System.out.println(x);
            this.coef_arr.add(x);
        }
    }

    public double polynomial_value_at_point(double x){
        double result = 0;
        int deg = 0;
        for (double coef : coef_arr){
            result += coef * Math.pow(x, deg);
            deg += 1;
        }
        return result;
    }

    public double get_coef(int index) {
        double coef = 0;
        if (index < 0) throw new IllegalArgumentException();
        try {
            coef = coef_arr.get(index);
        } catch (IndexOutOfBoundsException e) {  //IndexOutOfBoundsException
            //System.out.println("вы передали неверный индекс =" + e.getMessage());
        }
        return coef;
    }

    public int get_deg(){
        return this.coef_arr.size() - 1;
    }

    public void give_new_coef(int index, double new_coef) {
        if (index < 0) throw new IllegalArgumentException();
        try {
            coef_arr.set(index, new_coef);
        } catch (IndexOutOfBoundsException e) {
            //System.out.println("вы передали неверный индекс: " + e.getMessage());
            int len = index - coef_arr.size();
            for (int i = 0; i < len; i++){
                coef_arr.add(0d);
            }
            coef_arr.add(new_coef);
        }
    }

    public Polinom multiplication(double value){
        Polinom result = new Polinom();
        for (int i = 0; i < coef_arr.size(); i++){
            result.give_new_coef(i, coef_arr.get(i) * value);
        }
        return result;
    }

    public Polinom multiplication(Polinom poly) {
        Polinom result = new Polinom();
        for (int i = 0; i < coef_arr.size(); i++) {
            for (int j = 0; j < poly.coef_arr.size(); j++) {
                result.give_new_coef(i+j, this.get_coef(i) * poly.get_coef(j) + result.get_coef(i + j));
            }
        }
        return result;
    }

    public String toString(){
        ArrayList<String> str_info = new ArrayList<>();
        String str_pol;
        for (int i = 0; i < coef_arr.size(); i++){
            str_info.add(coef_arr.get(i)+"x^"+i);
        }
        str_pol = StringUtils.join(str_info, " + ");
        return str_pol;
    }

    public Polinom addition_of_polynomials(Polinom poly){
        Polinom result = new Polinom();
        for (int i = 0; i < max(coef_arr.size(),poly.get_deg() + 1); i++) {
            result.give_new_coef(i, this.get_coef(i) + poly.get_coef(i));
        }
        return result;
    }

    public Polinom derivative(){
        Polinom result = new Polinom();
        for (int i = 1; i < coef_arr.size(); i++) {
            result.give_new_coef(i-1, this.get_coef(i) * i);
        }
        return result;
    }

    public Polinom antiderivative(double constanta){
        Polinom result = new Polinom();
        int i;
        result.give_new_coef(0, constanta);
        for (i = 1; i < coef_arr.size() + 1; i++) {
            result.give_new_coef(i, this.get_coef(i-1) / i);
        }
        //result.give_new_coef(this.get_coef(i - 1)/i, i);
        return result;
    }

    public double Newton_Leibniz(double bottom, double top){
        return this.antiderivative(0).polynomial_value_at_point(top) - this.antiderivative(0).polynomial_value_at_point(bottom);
    }

    public double rectangle_method(double a, double b, double epsilon){
        double count = (b - a) / epsilon;
        double result = 0;
        double x_value = 0;
        for (int i = 0; i < count; i ++){
            x_value = a + i * epsilon;
            result += epsilon * this.polynomial_value_at_point(x_value);
        }
        return result;
    }

//    public void test(){
//        this.coef_arr.get(-1);
//    }
}
