package com.ashwinupadhyaya.bikepower;

public class Bike {
	
	float rollResCoeffV = (float)0.1;		// Coefficient for the dynamic rolling resistance, normalized to road inclination; CrVn = CrV*cos(β)
	float airDensity0 = (float)1.2922;		// Air density on sea level at 0° Celsius (32°F) kg/m3
	float airPressure0 = (float)101325;		// Air pressure on sea level at 0° Celsius (32°F) Pa
	float gVal = (float)9.806;				// Gravitational acceleration m/s2
	float coeffLoss = (float)1.03;			// Coefficient for power transmission losses and losses due to tire slippage (the latter can be heard while pedaling powerfully at low speeds)

	float velocity;			// Velocity of the bicycle
	float windSpeed;		// Wind speed
	float altitude;			// Height above sea level (influences air density)
	float temp;				// Air temperature, in Kelvin (influences air density)
	float grade;			// Inclination (grade) of road, in percent
	float massBike;			// Mass of the bicycle (influences rolling friction, slope pulling force, and normal force)
	float massRider;		// Mass of the rider (influences rolling friction, slope pulling force, and the rider's frontal area via body volume)

	float airDragCoeff; 	// Air drag coefficient
	float frontalArea;		// Total frontal area (bicycle + rider)
	float rollResCoeff; 	// Rolling resistance coefficient

	float beta;				// ("beta") Inclination angle, = arctan(grade/100)
	float rollFric;			// Rolling friction (normalized on inclined plane) plus slope pulling force on inclined plane
	float airDensity;		// ("rho") Air density
	float rollResCoeffVn;	// Coefficient for velocity-dependent dynamic rolling resistance, here approximated with 0.1
	float power;			// Rider's power

	public Bike(float velocity, float windSpeed, float altitude, 
			float temp, float grade, float massBike, float massRider) {
		super();
		this.velocity = velocity;
		this.windSpeed = windSpeed;
		this.altitude = altitude;
		this.temp = temp;
		this.grade = grade;
		this.massBike = massBike;
		this.massRider = massRider;
		this.beta = (float) Math.atan(grade);
		airDensity = getAirDensity();
		rollFric = getRollingFriction();
	}
	
	// Air density via barometric formula: 
	public float getAirDensity() {
		airDensity = (float) (airDensity0 * 373 / (temp + 273) * Math.exp((-1)*airDensity0*gVal*altitude/airPressure0));
		return airDensity;
	}
	
	// Rolling friction plus slope pulling force: 
	public float getRollingFriction() {
		rollFric = (float) (gVal * (massBike + massRider)*(rollResCoeff*Math.cos(beta) + Math.sin(beta)));
		return rollFric;
	}
	
	// Power Calculation
	public float getPower() {
		rollResCoeffVn = (float) (rollResCoeffV * Math.cos(beta));
//		power = (float) (coeffLoss * velocity * (airDragCoeff * frontalArea * airDensity / 2 * Math.pow(velocity + windSpeed, 2) + rollFric + velocity * rollResCoeffVn));
		//Approximations to get a first version working
		power = (float) (coeffLoss * velocity * (0.5 * airDensity / 2 * Math.pow(velocity + windSpeed, 2) + 0.003 + velocity * rollResCoeffVn));
		return power;
	}
}
