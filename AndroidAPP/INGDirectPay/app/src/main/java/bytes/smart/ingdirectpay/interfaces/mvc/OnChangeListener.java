package bytes.smart.ingdirectpay.interfaces.mvc;

/**
 * The listener interface for receiving onChange events.
 * The class that is interested in processing a onChange
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addOnChangeListener<code> method. When
 * the onChange event occurs, that object's appropriate
 * method is invoked.
 *
 * @param <T> the generic type
 * @author alexandru.buicescu
 */
public interface OnChangeListener<T> {

    /**
     * On change.
     */
    void onChange();
}
