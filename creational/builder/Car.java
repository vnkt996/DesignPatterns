// Product: The complex object to be built
public class Car {
    // Required parameters
    private String engine;
    private int wheels;
    
    // Optional parameters
    private String color;
    private boolean hasGPS;
    private boolean hasAirbags;

    // Private constructor to force the use of the Builder
    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.color = builder.color;
        this.hasGPS = builder.hasGPS;
        this.hasAirbags = builder.hasAirbags;
    }

    @Override
    public String toString() {
        return "Car [Engine=" + engine + ", Wheels=" + wheels + ", Color=" + color + 
               ", GPS=" + hasGPS + ", Airbags=" + hasAirbags + "]";
    }

    // Step-by-step Builder interface with method chaining
    public interface EngineStep {
        WheelsStep setEngine(String engine);
    }

    public interface WheelsStep {
        OptionalSteps setWheels(int wheels);
    }

    public interface OptionalSteps {
        OptionalSteps setColor(String color);
        OptionalSteps setGPS(boolean hasGPS);
        OptionalSteps setAirbags(boolean hasAirbags);
        Car build();  // Final method to return the constructed Car
    }

    // Concrete builder implementation with step-by-step chaining
    public static class CarBuilder implements EngineStep, WheelsStep, OptionalSteps {
        // Required parameters
        private String engine;
        private int wheels;

        // Optional parameters initialized to default values
        private String color = "White";
        private boolean hasGPS = false;
        private boolean hasAirbags = false;

        // Step 1: Set the engine
        @Override
        public WheelsStep setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        // Step 2: Set the wheels
        @Override
        public OptionalSteps setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        // Optional steps
        @Override
        public OptionalSteps setColor(String color) {
            this.color = color;
            return this;
        }

        @Override
        public OptionalSteps setGPS(boolean hasGPS) {
            this.hasGPS = hasGPS;
            return this;
        }

        @Override
        public OptionalSteps setAirbags(boolean hasAirbags) {
            this.hasAirbags = hasAirbags;
            return this;
        }

        // Final build method
        @Override
        public Car build() {
            return new Car(this);
        }
    }
}

// Usage of the Builder pattern with step-by-step chaining
public class BuilderPatternDemo {
    public static void main(String[] args) {
        Car car = new Car.CarBuilder()
                            .setEngine("V8")  // Required step 1
                            .setWheels(4)      // Required step 2
                            .setColor("Red")   // Optional
                            .setGPS(true)      // Optional
                            .setAirbags(true)  // Optional
                            .build();

        System.out.println(car);
    }
}
