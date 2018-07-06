package org.spongycastle.i18n;

public class LocalizedMessage {
    protected final String a;
    protected final String b;
    protected String c;
    protected FilteredArguments d;
    protected FilteredArguments e;
    protected ClassLoader f;

    protected class FilteredArguments {
        protected Object[] a;

        public Object[] a() {
            return this.a;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Resource: \"").append(this.b);
        stringBuffer.append("\" Id: \"").append(this.a).append("\"");
        stringBuffer.append(" Arguments: ").append(this.d.a().length).append(" normal");
        if (this.e != null && this.e.a().length > 0) {
            stringBuffer.append(", ").append(this.e.a().length).append(" extra");
        }
        stringBuffer.append(" Encoding: ").append(this.c);
        stringBuffer.append(" ClassLoader: ").append(this.f);
        return stringBuffer.toString();
    }
}
