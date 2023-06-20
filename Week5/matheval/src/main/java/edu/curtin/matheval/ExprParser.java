package edu.curtin.matheval;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Parses mathematical expressions, and builds a tree of ExprNode objects to
 * represent the parsed
 * expression.
 */
public class ExprParser {
    // The regex pattern used for tokenisation purposes. Basically, this skips any
    // amount of
    // whitespace, then checks for a fractional number (containing and possibly
    // starting with "."),
    // then checks for an integer, and finally falls back to a single other
    // character of any type.
    private static final Pattern TOKEN = Pattern.compile("\\s*([0-9]*\\.[0-9]+|[0-9]+|.)");

    private static final Logger logger = Logger.getLogger(ExprParser.class.getName());

    public ExprParser() {
    }

    /**
     * Parses a string, which is assumed to contain a mathematical expression.
     * Returns the root
     * node of the resulting object tree.
     * 
     * @throws UserInputException
     */
    public ExprNode parse(String s) throws UserInputException {

        logger.log(Level.INFO, "parse start");

        List<String> tokens = new LinkedList<>();

        // Tokenise the string, by repeatedly applying the 'TOKEN' regular expression
        // until it
        // doesn't match anymore (which should only happen at the end of the string).
        String substr = s;
        boolean done = false;
        do {
            Matcher matcher = TOKEN.matcher(substr);
            if (matcher.lookingAt()) {
                tokens.add(matcher.group(1));
                substr = substr.substring(matcher.end());
            } else {
                done = true;
            }
        } while (!done);

        // Invoke the actual parsing logic.
        return parseAdd(tokens);
    }

    /**
     * Parses a sequence of zero-or-more "+" / "-" operators (the lowest operator
     * precedence
     * level).
     * 
     * @throws UserInputException
     */
    private ExprNode parseAdd(List<String> tokens) throws UserInputException {

        logger.log(Level.INFO, "parseAdd start: " + tokens);

        ExprNode node = parseMul(tokens);
        logger.log(Level.INFO, "parseAddAfrMul: " + tokens);
        boolean end = false;
        while (!end && !tokens.isEmpty()) {
            // Expect next token to be '+' or '-'
            String token = tokens.remove(0);
            switch (token) {
                case "+":
                    logger.log(Level.INFO, "case +: " + tokens);
                    node = new AddOperator(node, parseMul(tokens));
                    break;

                case "-":
                    logger.log(Level.INFO, "case -: " + tokens);
                    node = new SubOperator(node, parseMul(tokens));
                    break;

                default:
                    logger.log(Level.INFO, "case adddefault: " + tokens);
                    // The next token isn't "+" or "-", which means we assume this additive
                    // sequence is over, so push the token back onto the list, and end.
                    if (token.equals("x")) {
                        throw new UserInputException("missing operator");
                    } else if (!token.equals(")")) {
                        throw new UserInputException("invalid character " + token);
                    } else {
                        tokens.add(0, token);
                        end = true;
                    }
                    break;
            }
        }
        return node;
    }

    /**
     * Parses a sequence of zero-or-more "*" / "/" operators.
     */
    private ExprNode parseMul(List<String> tokens) throws UserInputException {

        logger.log(Level.INFO, "parseMul start: " + tokens);

        ExprNode node = parsePrimary(tokens);
        boolean end = false;
        while (!end && !tokens.isEmpty()) {
            // Expect next token to be "*" or "/"
            String token = tokens.remove(0);
            switch (token) {
                case "*":
                    logger.log(Level.INFO, "case *: " + tokens);
                    node = new MulOperator(node, parsePrimary(tokens));
                    break;

                case "/":
                    logger.log(Level.INFO, "case /: " + tokens);
                    node = new DivOperator(node, parsePrimary(tokens));
                    break;

                default:
                    logger.log(Level.INFO, "case Muldefault: " + tokens);
                    // The next token isn't "*" or "/", which means we assume this multiplicative
                    // sequence is over, so push the token back onto the list, and end.
                    tokens.add(0, token);
                    end = true;
                    break;
            }
        }
        return node;
    }

    /**
     * Parses a "primary" value, which can be either a sub-expression in brackets, a
     * negation "-"
     * operator, a reference to the "x" variable, or a literal number.
     */
    private ExprNode parsePrimary(List<String> tokens) throws UserInputException {

        logger.log(Level.INFO, "parsePrimary start: " + tokens);

        ExprNode node;
        String token;
        try {
            token = tokens.remove(0); // Obtain the next token
        } catch (IndexOutOfBoundsException e) {
            throw new UserInputException("incomplete equation", e);
        }
        switch (token) {
            case "(":
                logger.log(Level.INFO, "case (: " + tokens);
                // Sub-expression inside brackets.
                node = parseAdd(tokens);
                try {
                    tokens.remove(0); // Remove closing ")"
                } catch (IndexOutOfBoundsException e) {
                    throw new UserInputException("bracket not closed", e);
                }
                break;

            case "-":
                logger.log(Level.INFO, "case mul-: " + tokens);
                // Inverted value (e.g., -(x+1))
                node = new NegationOperator(parsePrimary(tokens));
                break;

            case "x":
                logger.log(Level.INFO, "case x: " + tokens);
                // Variable value
                node = new XValue();
                break;

            default:
                logger.log(Level.INFO, "case Pridefault: " + tokens);
                // Literal number
                try {
                    node = new Value(Double.parseDouble(token));
                } catch (NumberFormatException e) {
                    throw new UserInputException("unexpected token '" + token + "'.", e);
                }
                break;
        }
        return node;
    }
}
