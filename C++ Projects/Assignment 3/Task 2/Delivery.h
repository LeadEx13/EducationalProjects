#pragma once
class Delivery
{
private:
	int distance, weight;
public:
	Delivery(int distance, int weight) {this->distance = distance; this->weight = weight; }
	Delivery(){ this->distance = 0; this->weight = 0; }
	void set(int distance, int weight){ this->distance = distance; this->weight = weight; }
	int salarysum(){ int salary = 0; salary = distance * 2 + 100; return salary; }
	int getweight(){ return weight; }
	int getdistance(){ return distance; }
};
