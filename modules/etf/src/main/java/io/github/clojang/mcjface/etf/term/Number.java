package io.github.clojang.mcjface.etf.term;

public sealed interface Number extends Term 
    permits Number.Integer, Number.Long, Number.Double, Number.BigInteger {
    
    java.lang.Number numberValue();
    
    default int intValue() {
        return numberValue().intValue();
    }
    
    default long longValue() {
        return numberValue().longValue();
    }
    
    default double doubleValue() {
        return numberValue().doubleValue();
    }
    
    default float floatValue() {
        return numberValue().floatValue();
    }
    
    default short shortValue() {
        return numberValue().shortValue();
    }
    
    default byte byteValue() {
        return numberValue().byteValue();
    }
    
    record Integer(int value) implements Number {
        @Override
        public java.lang.Number numberValue() {
            return value;
        }
        
        @Override
        public byte[] encode() {
            return new byte[0]; // Stub implementation
        }
    }
    
    record Long(long value) implements Number {
        @Override
        public java.lang.Number numberValue() {
            return value;
        }
        
        @Override
        public byte[] encode() {
            return new byte[0]; // Stub implementation
        }
    }
    
    record Double(double value) implements Number {
        @Override
        public java.lang.Number numberValue() {
            return value;
        }
        
        @Override
        public byte[] encode() {
            return new byte[0]; // Stub implementation
        }
    }
    
    record BigInteger(java.math.BigInteger value) implements Number {
        @Override
        public java.lang.Number numberValue() {
            return value;
        }
        
        @Override
        public byte[] encode() {
            return new byte[0]; // Stub implementation
        }
    }
}
