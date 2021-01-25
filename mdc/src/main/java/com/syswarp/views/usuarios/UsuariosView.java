package com.syswarp.views.usuarios;

import java.util.Optional;

import com.syswarp.data.entity.Usuarios;
import com.syswarp.data.service.UsuariosService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import com.syswarp.views.main.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

@Route(value = "usuarios", layout = MainView.class)
@PageTitle("Usuarios")
@CssImport("./styles/views/usuarios/usuarios-view.css")
public class UsuariosView extends Div {

    private Grid<Usuarios> grid = new Grid<>(Usuarios.class, false);

    private TextField id;
    private TextField email;
    private TextField clave;
    private TextField nombre;
    private TextField habilitado;
    private TextField idtipousuario;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Usuarios> binder;

    private Usuarios usuarios;

    public UsuariosView(@Autowired UsuariosService usuariosService) {
        setId("usuarios-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("email").setAutoWidth(true);
        grid.addColumn("clave").setAutoWidth(true);
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("habilitado").setAutoWidth(true);
        grid.addColumn("idtipousuario").setAutoWidth(true);
        grid.setItems(query -> usuariosService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Usuarios> usuariosFromBackend = usuariosService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (usuariosFromBackend.isPresent()) {
                    populateForm(usuariosFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Usuarios.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(id).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("id");
        binder.forField(idtipousuario).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("idtipousuario");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.usuarios == null) {
                    this.usuarios = new Usuarios();
                }
                binder.writeBean(this.usuarios);

                usuariosService.update(this.usuarios);
                clearForm();
                refreshGrid();
                Notification.show("Usuarios details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the usuarios details.");
            }
        });

    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        id = new TextField("Id");
        email = new TextField("Email");
        clave = new TextField("Clave");
        nombre = new TextField("Nombre");
        habilitado = new TextField("Habilitado");
        idtipousuario = new TextField("Idtipousuario");
        Component[] fields = new Component[]{id, email, clave, nombre, habilitado, idtipousuario};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Usuarios value) {
        this.usuarios = value;
        binder.readBean(this.usuarios);

    }
}
