import java.util.ArrayList;

public class second_task {
    public static void main(String[] args){
        ArrayList<Double> array_for_polinom = new ArrayList<>();
        ArrayList<Double> array_for_polinom2 = new ArrayList<>();
        for (double i = 1; i < 5; i += 1){
            array_for_polinom.add(i);
        }

        Polinom empty_pol = new Polinom();
        Polinom pol1 = new Polinom(array_for_polinom);
        Polinom pol2 = new Polinom(5);

        System.out.println("вычисляем значение полинома в точке:");
        System.out.println("empty_pol =" + empty_pol.polynomial_value_at_point(1000));
        System.out.println(pol1);
        System.out.println("pol1 = "+pol1.polynomial_value_at_point(1));
        System.out.println("pol1 = "+pol1.polynomial_value_at_point(2));
        System.out.println("pol2 = "+pol2.polynomial_value_at_point(1));


        System.out.println("Умножение на скаляр");
        System.out.println(pol1.get_coef(3));
        pol1.multiplication(5);
        System.out.println(pol1.get_coef(3));


        System.out.println("=============================================================");
        ArrayList<Double> array_for_mult1 = new ArrayList<>();
        ArrayList<Double> array_for_mult2 = new ArrayList<>();

        double[] elements1 = {1.0, 1.0};
        double[] elements2 = {1.0, 3.0, 2.0};

        for (double i: elements1){
            array_for_mult1.add(i);
        }

        for (double i: elements2){
            array_for_mult2.add(i);
        }
        Polinom pol_for_something1 = new Polinom(array_for_mult1);
        Polinom pol_for_something2 = new Polinom(array_for_mult2);

        Polinom result_of_mult = pol_for_something1.multiplication(pol_for_something2);
        System.out.println("перемножение = "+ result_of_mult);

        Polinom result_of_addition = pol_for_something1.addition_of_polynomials(pol_for_something2);
        System.out.println("сложение = " + result_of_addition);

        Polinom result_of_differentiation = pol_for_something2.derivative();
        System.out.println("производная = "+ result_of_differentiation);

        Polinom result_of_antiderivative = pol_for_something2.antiderivative(0);
        System.out.println("первообразная с заданной константой = " + result_of_antiderivative);
        System.out.println("по Ньютона-Лейбница = " + pol_for_something2.Newton_Leibniz(0, 1));

        System.out.println("Метод прямоугольников. Первообразная = " + pol_for_something2.rectangle_method(0, 1, 0.01));
        try {
            pol1.get_coef(-1);
        }catch (IllegalArgumentException e){
            System.out.println("вы передали неверный индекс: "+ e.getMessage());
        }
        System.out.println(pol1.toString());

        //pol1.give_new_coef(-1, 4.5);  //- проверяем падает ли программа при отрицательном переданном индексе
        Polinom test_poly = pol1.multiplication(5);
        System.out.println(test_poly);
    }
}
