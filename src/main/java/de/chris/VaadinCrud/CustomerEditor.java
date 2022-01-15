package de.chris.VaadinCrud;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.plaf.basic.BasicMenuUI;

@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout implements KeyNotifier
{
    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");

    private Button save = new Button("Save", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Cancel");
    private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private Binder<Customer> binder = new Binder<>(Customer.class);
    private ChangeHandler changeHandler;

    public CustomerEditor()
    {

        this.add(firstName,lastName,actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCustomer(customer));
        setVisible(false);
    }

    private void save()
    {
        customerRepository.save(customer);

    }

    private void delete()
    {
        customerRepository.delete(customer);
    }

    public void editCustomer(Customer customer)
    {
        if(customer == null)
        {
            setVisible(false);
            return;
        }
        final boolean persisted = customer.getCustomerId() != null;
        if (persisted) {
            // Find fresh entity for editing
            this.customer = customerRepository.findById(customer.getCustomerId()).get();
        }
        else {
            this.customer = customer;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(this.customer);

        setVisible(true);

        // Focus first name initially
        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

}
