package shapes;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * class Line.
 */
public class Line implements Shapes {
    //members
    private Point start;
    private Point end;
    private Color color;

    /**
     * @param start -the start point of the line
     * @param end   -the   end line of the point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @return the color of the line
     */
    public Color getColor() {
        return color;
    }

    /**
     * change the color of the line.
     *
     * @param color1 the color to chane to
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * define by the exact value of each point.
     *
     * @param x1 -x value of the start point
     * @param y1 -y value of the start point
     * @param x2 -x value of the end point
     * @param y2 -y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        double dis;
        dis = (Math.pow((this.start.getX() - this.end.getX()), 2) + Math.pow((this.start.getY() - this.end.getY()), 2));
        dis = Math.sqrt(dis);
        return dis;
    }

    /**
     * @return the middle of the line
     */
    public Point middle() {
        Point mid;
        double midX, midY;
        midX = ((this.start.getX() + this.end.getX()) / 2);
        midY = ((this.start.getY() + this.end.getY()) / 2);
        mid = new Point(midX, midY);
        return mid;
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        Point str = new Point(this.start.getX(), this.start.getY());
        return str;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        Point theEnd = new Point(this.end.getX(), this.end.getY());
        return theEnd;
    }

    /**
     * @param first -first point on the line
     * @param last  -second point of the line
     * @return the incline of the line
     */
    public double incline(Point first, Point last) {
        double inc;
        inc = ((first.getY() - last.getY()) / (first.getX() - last.getX()));
        return inc;
    }

    /**
     * @param inLine -a point on the line
     * @param inc    -the  incline of the line
     * @return the equalation of the line
     */
    public double equasionOfLine(Point inLine, double inc) {
        double equa;
        equa = inLine.getY() - (inc * inLine.getX());
        return equa;
    }

    /**
     * @param other - the line to check if there intersecting
     * @return true if the lines Intersecting and false id not
     */
    public boolean isIntersecting(Line other) {
        Point meet;
        meet = (this.intersectionWith(other));
        if (meet != null) {
            return true;
        }
        return false;


    }

    /**
     * @param meet  - the meeting point
     * @param other -the second line
     * @return true if the lines are crossing and false if not
     */
    public boolean isCrossing(Point meet, Line other) {
        //the meet point between the lines
        double meetY = meet.getY();
        double meetX = meet.getX();
        //if the line is vertical
        if (this.start.getY() == this.end.getY()) {
            if ((meetX < this.start.getX()) || (meetX > this.end.getX())) {
                return false;
            }

        }
        //if the line is vertical
        if (other.start.getY() == other.end.getY()) {
            if ((meetX < other.start.getX()) || (meetX > other.end.getX())) {
                return false;
            }

        }

        if (other.start.getY() <= other.end.getY()) {
            if (this.start.getY() <= this.end.getY()) {
                if ((meetY < other.start.getY()) || (meetY < this.start.getY())) {
                    return false;
                }
                if ((meetY > other.end.getY()) || (meetY > this.end.getY())) {
                    return false;
                }


            } else {
                if ((meetY < this.end.getY()) || (meetY < other.start.getY())) {
                    return false;
                }
                if ((meetY > this.start.getY()) || (meetY > other.end.getY())) {
                    return false;
                }
            }
            if (other.start.getY() == other.end.getY()) {
                if ((meetX < other.start.getX()) || (meetX > other.end.getX())) {
                    return false;
                }
            }
        } else {
            //both have neg incline
            if (this.start.getY() >= this.end.getY()) {
                if ((meetY < this.end.getY()) || (meetY < other.end.getY())) {
                    return false;
                }
                if ((meetY > this.start.getY()) || (meetY > other.start.getY())) {
                    return false;
                }
            } else {
                //one neg incline and one positive
                if ((meetY > other.start.getY()) || (meetY > this.end.getY())) {
                    return false;
                }
                if ((meetY < other.end.getY()) || (meetY < this.start.getY())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param other -the second line to check if there crossing
     * @return the meeting point between the lines
     */
    public Point intersectionWith(Line other) {
        double inc, incNew, b, bNew, xOfInter, yOfInter, y;
        Point inter;
        //if the incline isn't defined
        if (this.start.getX() == this.end.getX()) {
            if (other.start.getX() == other.end.getX()) {
                return null;
            }
            incNew = incline(other.start, other.end);
            bNew = equasionOfLine(other.start, incNew);
            y = incNew * this.start.getX() + bNew;
            if ((y >= this.start.getY() && (y <= this.end.getY()))) {
                Point meet = new Point(this.start.getX(), y);
                if (this.isCrossing(meet, other)) {
                    return meet;
                }
            }
            return null;
        } else {
            if (other.start.getX() == other.end.getX()) {
                incNew = incline(this.start, this.end);
                bNew = equasionOfLine(this.start, incNew);
                y = incNew * other.start.getX() + bNew;
                if ((y >= other.start.getY() && (y <= other.end.getY()))) {
                    Point meet = new Point(other.start.getX(), y);
                    if (this.isCrossing(meet, other)) {
                        return meet;
                    }
                }
                return null;
            }
        }
        inc = incline(this.start, this.end);
        incNew = incline(other.start, other.end);
        //if the lines are parallel
        if (inc == incNew) {
            return null;
        } else {
            b = equasionOfLine(this.start, inc);
            bNew = equasionOfLine(other.start, incNew);
            xOfInter = xValue(inc, b, incNew, bNew);
            yOfInter = yValue(inc, b, xOfInter);
            inter = new Point(xOfInter, yOfInter);
        }
        if (isCrossing(inter, other)) {
            return inter;
            //there id no meeting point
        } else {
            return null;
        }
    }

    /**
     * @param inc     -the incline of the first line
     * @param b       -a part of the equation of the first line
     * @param incline -the incline of the second line
     * @param n       -a part of the equation of the second line
     * @return the x value
     */
    public double xValue(double inc, double b, double incline, double n) {
        double x;
        x = (b - n) / (incline - inc);
        return x;
    }

    /**
     * @param inc the incline of the line
     * @param b   a part of the equation of the line
     * @param x   -x value
     * @return the y value
     */
    public double yValue(double inc, double b, double x) {
        double y;
        y = (inc * x) + b;
        return y;
    }

    /**
     * @param other -the line to check if there equals
     * @return true if the lines are equals and false if not
     */
    public boolean equals(Line other) {
        if ((other.start.getX() == this.start.getX()) && (other.end.getX() == this.start.getX())) {
            if ((other.start.getY() == this.start.getY()) && (other.end.getY() == this.end.getY())) {
                return true;
            }
        }
        return false;
    }
    /*public Point smallDis(List<Point> points){
        if (points.size()==0){
            return null;
        }
        List dis=new ArrayList();
        for (int i=0; i<points.size();i++){
            dis.add(this.start.distance(points.get(i)));

        }
        double smallDis=(double)dis.get(0);
        Point theClosestPoint=(Point)points.get(0);
        for(int i=1; i<dis.size(); i++){
            if (smallDis>(double) dis.get(i)){
                smallDis=(double) dis.get(i);
                theClosestPoint=(Point)points.get(i);
            }
        }
        return theClosestPoint;
    }*/

    /**
     * @param rect rectangle to check if crossing with the line.
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double dista, otherDista;
        Line line = new Line(this.start, this.end);
        List listPoint = rect.intersectionPoints(line);
        if (listPoint != null) {
            if (listPoint.size() == 1) {
                return (Point) (listPoint.get(0));
            }
            dista = this.start.distance((Point) (listPoint.get(0)));
            otherDista = this.start.distance((Point) (listPoint.get(1)));


            if ((dista <= otherDista) && (listPoint.get(0) != null)) {
                return (Point) (listPoint.get(0));
            }
            if (listPoint.get(1) != null) {
                return (Point) (listPoint.get(1));
            }
        }
        return null;
    }

    /**
     * @param meet the meeting point
     * @return true if the point is on the line and false if not
     */
    public boolean isPointOnLine(Point meet) {
        double dis, dista;
        dis = this.start().distance(meet);
        dista = this.end().distance(meet);

        if ((Math.abs(this.length() - (dis + dista))) < 0.0001) {
            return true;
        }
        return false;
    }

    /**
     * draw the line.
     *
     * @param d DrawSurface to draw with
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.drawLine((int) this.start.getX(), (int) this.start.getY(),
                (int) this.end.getX(), (int) this.end.getY());
    }

    /**
     * add the line to a shape list.
     *
     * @param shapeList the list to add to
     */
    @Override
    public void addToList(List<Shapes> shapeList) {
        shapeList.add(this);
    }

    /**
     * @return chane the printing way
     */
    public String toString() {
        String stringRank;
        stringRank = ("start Point: (" + String.valueOf(this.start.getX()) + ","
                + String.valueOf(this.start.getY()) + ")"
                + " end Point: (" + String.valueOf(this.end.getX()) + "," + String.valueOf(this.end.getY()) + ")");
        return stringRank;
    }
}


