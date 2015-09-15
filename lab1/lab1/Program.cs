using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab1
{
    class Triangle
    {
        static void Main(string[] args)
        {
            if (args.Length < 3 || args.Length > 3)
            {
                Console.WriteLine("Wrong parameters number");
                return;
            }
            m_coords = new List<Coord>();
            m_lengths = new List<double>();
            foreach (string arg in args)
            {
                try
                {
                    m_coords.Add(ParseCoordinate(arg));
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.Message);
                    return;
                }
            }
            m_lengths.Add(GetSegmentLength(m_coords[0], m_coords[1]));
            m_lengths.Add(GetSegmentLength(m_coords[0], m_coords[2]));
            m_lengths.Add(GetSegmentLength(m_coords[1], m_coords[2]));
            if (IsOnOneLine())
            {
                Console.WriteLine("Dots places on same line");
                return;
            }
            PrintTriangleType();
        }

        static double GetSegmentLength(Coord s1, Coord s2)
        {
            return Math.Sqrt(Math.Pow((s2.x - s1.x), 2) + Math.Pow((s2.y - s1.y), 2));
        }

        static bool IsEqual(double d1, double d2)
        {
            return Math.Abs(d1 - d2) < eps;
        }

        static bool IsOnOneLine()
        {
            return (m_lengths[0] + m_lengths[1] <= m_lengths[2] ||
                m_lengths[0] + m_lengths[2] <= m_lengths[1] ||
                m_lengths[2] + m_lengths[1] <= m_lengths[0]);
        }

        static void PrintTriangleType()
        {
            foreach (double len in m_lengths)
            {
                if (len == 0)
                {
                    Console.WriteLine("It is not a triangle");
                    return;
                }
            }
            if (IsEqual(m_lengths[0], m_lengths[1]) &&
                IsEqual(m_lengths[0], m_lengths[2]))
            {
                Console.WriteLine("Equilateral triangle");
                return;
            }
            
            if (IsEqual(m_lengths[0], m_lengths[1]) ||
                IsEqual(m_lengths[0], m_lengths[2]) ||
                IsEqual(m_lengths[1], m_lengths[2]))
            {
                Console.WriteLine("Isosceles triangle");
                return;
            }
            
            Console.WriteLine("Simple triangle");
          
        }

        static Coord ParseCoordinate(string coordStr)
        {
            Coord result;
            string xStr = "";
            string yStr = "";
            bool isX = true;
            foreach (char c in coordStr)
            {
                if (isX)
                {
                    if (c == ',')
                    {
                        isX = false;
                    }
                    else
                    {
                        if (c == '.')
                        {
                            xStr += ',';
                        }
                        else
                        {
                            xStr += c;
                        }
                    }
                }
                else
                {
                    if (c == ',')
                    {
                        throw new System.ArgumentException("Wront coordinate format");
                    }
                    if (c == '.')
                    {
                        yStr += ',';
                    }
                    else
                    {
                        yStr += c;
                    }
                }
            }
            try
            {
                result.x = double.Parse(xStr);
                result.y = double.Parse(yStr);
            }
            catch
            {
                throw new System.ArgumentException("Wrong coordinate format");
            }
            return result;
        }

        private struct Coord
        {
            public double x;
            public double y;
        }

        static List<Coord> m_coords;
        static List<double> m_lengths;
        const double eps = 0.001;
    }
}
