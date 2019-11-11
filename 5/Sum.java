import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Sum extends Node {
    private List<Node> args = new ArrayList<>();

    public Sum() {}

    public Sum(Node nodes) {
        this.add(nodes);
    }

    public Sum(double constants) {
        this.add(constants);
    }

    public Sum(double constant, Node node) {
        this.add(constant, node);
    }

    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    public Sum add(double constant, Node node) {
        args.add(new Prod(constant, node));
        return this;
    }

    @Override
    double evaluate() {
        return sign.getValue() * args.stream().mapToDouble(Node::evaluate).sum();
    }

    @Override
    int getArgumentsCount() {
        return args.size();
    }

    @Override
    Node diff(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            r.add(n.diff(var));
        }
        return r;
    }

    @Override
    boolean isDiffZero(Variable variable) {
        return args.stream().allMatch(node -> node.isDiffZero(variable));
    }

    @Override
    public String toString() {
        if (args.size() == 0) {
            return new Constant(0).toString();
        }

        StringBuilder builder = new StringBuilder("");

        if (sign == Sign.MINUS) {
            builder.append(sign.getStringValue());
            builder.append("(");
        }

        StringJoiner joiner = new StringJoiner(" + ");
        args.stream()
                .filter(node -> !node.toString().equals("0") && !node.toString().isEmpty())
                .forEach(node -> joiner.add(node.toString()));
        builder.append(joiner.toString());

        if (sign == Sign.MINUS) {
            builder.append(")");
        }

        return builder.toString();
    }
}