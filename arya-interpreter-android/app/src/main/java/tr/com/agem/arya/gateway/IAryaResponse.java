package tr.com.agem.arya.gateway;

public interface IAryaResponse
{
    void fromXMLString(String xmlString);
    String toString();
    String getView();
    String getData();
}
