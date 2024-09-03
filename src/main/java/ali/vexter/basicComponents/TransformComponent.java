package ali.vexter.basicComponents;

import ali.vexter.ECS.Component;
import ali.vexter.ECS.Entity;
import ali.vexter.tools.Transform;

public class TransformComponent extends Component {
   private Transform transform;

   public TransformComponent(Entity owner, Transform transform) {
      super(owner);
      this.transform = transform;
   }

   public Transform getTransform() {
      return this.transform;
   }
}
