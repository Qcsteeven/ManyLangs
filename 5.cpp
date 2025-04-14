#include <iostream>
#include <vector>
#include <cmath>

double pol(double x, const std::vector<double>& coeffs) {
    double result = 0;
    for(int i = 0; i < coeffs.size(); i++) {
        result += coeffs[i] * pow(x, coeffs.size()-1-i);
    }
    return result;
}

std::vector<double> solve(double a, double b, double eps, std::initializer_list<double> coeffs) {
    std::vector<double> roots;
    std::vector<double> c(coeffs);
    double step = eps * 10;
    
    for(double x = a; x < b; x += step) {
        double left = x, right = x + step;
        
        double p_a = pol(left, c);
        double p_b = pol(right, c);
        
        if(p_a * p_b > 0) continue;
        
        while(right - left > eps) {
            double mid = (left + right)/2;
            double f_mid = pol(mid, c);
            if(fabs(f_mid) < eps) {
                left = mid;
                break;
            }
            if(p_a * f_mid < 0) {
                right = mid;
                p_b = f_mid;
            } else {
                left = mid;
                p_a = f_mid;
            }
        }
        double root = round(left * 1000)/1000;
        if(roots.empty() || fabs(roots.back()-root) > eps) {
            if (root == 0) {
                roots.push_back(0);
            } else {
                roots.push_back(root);
            }
            
        }
            
    }
    
    return roots;
}

int main() {
    auto r = solve(-5,5, 0.0001, {1,-1,-6});
    for(double x : r) std::cout << x << " ";
    std::cout << std::endl;
    
    r = solve(-5,5, 0.0001, {1,0,0,0});
    for(double x : r) std::cout << x << " ";
}
